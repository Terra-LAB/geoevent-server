package geocollector.util;

public class Links {
	
	public final static String DATABASE = "/address.cfg.xml";
	private static String HOMEPATH = "";
	//private static String HOMEPATH = "/server";
	
	public final static String LOGIN_ACCOUNT_ACTIVATED= HOMEPATH+"/login.jsp?ativado=true";
	
	public final static String LOGIN_ERRO= HOMEPATH+"/login.jsp?erro=email";

	public final static String ERRO = HOMEPATH+"/erro.jsp";
	
	public final static String USER_INFORMACOESPONTO = HOMEPATH+"/user/informacoesPonto.jsp";
	
	public final static String USER_HOME = HOMEPATH+"/user/home.jsp";
	
	public final static String USER_NOTREGISTERED = HOMEPATH+"/jsp/user-notregistered.jsp";
	
	public final static String HOME_INDEX = HOMEPATH+"/index.jsp";
	
	public final static String LOGOUT = HOMEPATH+"/logout";

}
