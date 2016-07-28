package geocollector.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public class MyKml extends Kml {
        private transient Marshaller m = null;
        private transient JAXBContext jc = null;
        
        private Marshaller createMarshaller()  throws JAXBException
            {
                if (m == null) {
                    m = getJaxbContext().createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyKml.NameSpaceBeautyfier());
                }
                return m;
            }
         private JAXBContext getJaxbContext()
                        throws JAXBException
                    {
                        if (jc == null) {
                            jc = JAXBContext.newInstance((Kml.class));
                        }
                        return jc;
                    }
         
        @Override
        public boolean marshal(OutputStream outputstream)throws FileNotFoundException {
                try {
            m = this.createMarshaller();
            m.setProperty("jaxb.encoding", "Unicode");
            m.setProperty("com.sun.xml.bind.marshaller.CharacterEscapeHandler", new NullCharacterEscapeHandler());
            m.marshal(this, outputstream);
            return true;
        } catch (JAXBException _x) {
            _x.printStackTrace();
            return false;
        }
                

        }


        private static class NullCharacterEscapeHandler implements CharacterEscapeHandler {

            public NullCharacterEscapeHandler() {
                super();
            }


            public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
                writer.write( ch, start, length );
            }
        }
        
        private final static class NameSpaceBeautyfier
			    extends NamespacePrefixMapper
			{


	    /**
	     * Internal method!
	     * <p>Customizing Namespace Prefixes During Marshalling to a more readable format.</p>
	     * <p>The default output is like:</p>
	     * <pre>{@code&lt;kml ... xmlns:ns2="http://www.w3.org/2005/Atom" xmlns:ns3="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:ns4="http://www.google.com/kml/ext/2.2"&gt;}</pre>
	     * <p>is changed to:</p>
	     * <pre>{@code &lt;kml ... xmlns:atom="http://www.w3.org/2005/Atom" xmlns:xal="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:gx="http://www.google.com/kml/ext/2.2"&gt;}</pre><p>What it does:</p>
	     * <p>namespaceUri: http://www.w3.org/2005/Atom              prefix: atom</p><p>namespaceUri: urn:oasis:names:tc:ciq:xsdschema:xAL:2.0 prefix: xal</p><p>namespaceUri: http://www.google.com/kml/ext/2.2        prefix: gx</p><p>namespaceUri: anything else prefix: null</p>
	     * 
	     */
	    @Override
	    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
	        if (namespaceUri.matches("http://www.w3.org/\\d{4}/Atom")) {
	            return "atom";
	        }
	        if (namespaceUri.matches("urn:oasis:names:tc:ciq:xsdschema:xAL:.*?")) {
	            return "xal";
	        }
	        if (namespaceUri.matches("http://www.google.com/kml/ext/.*?")) {
	            return "gx";
	        }
	        return null;
	    }
	}
}