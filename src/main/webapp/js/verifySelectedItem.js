$(document).ready(function(){
	$(".dropdown-menu li a").click( function() {
		var action = $(this).text();

		if(action == "Campo de texto"){
			addInputField("insertHere");
			alert("chegou");
			
		/*	insertTextField();
			var inputField = "<div class='form-group'> <label for='inputEmail'>Email</label> <input class='form-control' placeholder=''> <span class='glyphicon glyphicon-remove' aria-hidden='true'></span></div>";
			$(".insertHere").append(inputField);*/
		}
		else if (action == "Lista de elementos"){
		
		}
		else if(removeSymbol(action) == "Mltipla escolha"){

		}
	});
});

/* Remove s�mbolos (acentua��o) de uma string */
function removeSymbol(string){
	var desired = string.replace(/[^\w\s]/gi, '');
	alert(desired);
	return desired;
}

function insertTextField(){
	
}