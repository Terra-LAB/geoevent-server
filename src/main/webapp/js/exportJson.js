function exportJson(){
	var $inputs = $('#insertHere :input');

	var $content ="";
	var $field = "";
	var $flagGetItens = 0; 
	var $classType = "";	
	var $jsonForm = "{ \"form\" : [";
	
	var $started = 0;

	$inputs.each(function(index)
	{	
	
		/* Verifica qual tip
		o de INPUT é o campo */ 
		

	//	var className = '.'+ $(this).attr('class').split(' ').join('.');
	//	alert(className);
		
		if( $(this).hasClass("btn") ){
			return true;
		}
	
		/* Significa que acabou os itens  da lista*/
		if ($(this).hasClass("itemLista") == false && $flagGetItens == 1){
			$flagGetItens = 0;
			$jsonForm = $jsonForm.substr(0, $jsonForm.length - 1);
			$jsonForm += "]},";
		}
		
		if($(this).hasClass("itemLista")){
		alert("entrou3");
			$content = $(this).val();
			$field = "{ \"val\" : \"" + $content + "\"},";
			$jsonForm += $field;
		}
		else if($flagGetItens == 1){
			$flagGetItens = 0;
			$jsonForm = $jsonForm.substr(0, $jsonForm.length - 1);
			$jsonForm += "]} ]},";
		}
		else if($(this).hasClass('textField')) {	
			$content = $(this).val();
			$field = "{ \"input_type\" : \"textField\", \"content\" : \"" + $content + "\" },";
			$jsonForm += $field;
		}
		else if ($(this).hasClass('listField')) {
			alert("entrou5");
			$content = $(this).val();
			$field = "{ \"input_type\" : \"listField\", \"content\" : \"" + $content + "\", \"values\" : [";
			$flagGetItens = 1;
			$jsonForm += $field;
		}
		else if ($(this).hasClass("multipleField")) {
			$content = $(this).val();
			$field = "{ \"input_type\" : \"multipleField\", \"content\" : \"" + $content + "\", \"values\" : [";
			$flagGetItens = 1;
			$jsonForm += $field;
		}
	});
	
	if($flagGetItens == 1){
		$jsonForm = $jsonForm.substr(0, $jsonForm.length - 1);
		$jsonForm += "]},";
	}



	var $lastChar = $jsonForm.substr($jsonForm.length - 1);
	if($lastChar == ","){
		$jsonForm = $jsonForm.substr(0, $jsonForm.length - 1);
	}


	$jsonForm += " ]}";

	alert($jsonForm);

}