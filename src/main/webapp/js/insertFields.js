/*Variável global*/

/* i : Id dos inputs principais
   j : Id das opções dos inputs principais
   */
var i=0;
var j=0;

/* Verifica qual tipo de campo foi clicado pelo usuário para adicionar*/
$(document).ready(function(){
	$(".dropdown-menu li a").click( function() {
		var action = $(this).text();

		if(action == "Campo de texto"){
			addInputField("insertHere");
		}
		else if (action == "Lista de elementos"){
			addMultipleField("insertHere", "Lista de elementos", "listField");
		}
		else if(removeSymbol(action) == "Mltipla escolha"){
			addMultipleField("insertHere", "Multiplica escolha", "multipleField");
		}
	});
});

/* Remove símbolos (acentuação) de uma string */
function removeSymbol(string){
	var desired = string.replace(/[^\w\s]/gi, '');
	return desired;
}

function incrementI(){
i +=1;                         /* function for automatic increament of feild's "Name" attribute*/                 
}

function incrementJ(){
j +=1;                         /* function for automatic increament of feild's "Name" attribute*/                 
}

/* Adiciona uma subopção */
function addItem(parentDiv){

    var divExterna = document.createElement("div");
	divExterna.setAttribute("class", "row up5");
	divExterna.setAttribute("id", "itemList_"+j);
	
	var primeiraDiv = document.createElement("div");
	primeiraDiv.setAttribute("class", "col-md-3");
	
	var segundaDiv = document.createElement("div");
	segundaDiv.setAttribute("class", "col-md-4");	
	
	var terceiraDiv = document.createElement("div");
	terceiraDiv.setAttribute("class", "col-md-4");
	
	var inputField = document.createElement("input");
	inputField.setAttribute("class", "form-control itemLista");
	inputField.setAttribute("placeholder", "Opção");
	
	var removeCampo = document.createElement("span");
	removeCampo.setAttribute("class" , "glyphicon glyphicon-remove");
	removeCampo.setAttribute("aria-hidden", "true");
	removeCampo.setAttribute("onclick", "removeElement('"+parentDiv+"', 'itemList_"+j+"')");

	
	
	segundaDiv.appendChild(inputField);
	terceiraDiv.appendChild(removeCampo);
	
	divExterna.appendChild(primeiraDiv);
	divExterna.appendChild(segundaDiv);
	divExterna.appendChild(terceiraDiv);

	$('#'+parentDiv).append(divExterna);
	incrementJ()
}

/* Cria um InputField (campo texto simples) no final do bloco DIV */
function addInputField(parentDiv){
	var divExterna = document.createElement("div");
	divExterna.setAttribute("class", "row up5");
	divExterna.setAttribute("id", "id_" + i);
	
	
	var primeiraDiv = document.createElement("div");
	primeiraDiv.setAttribute("class", "col-md-3");
	primeiraDiv.innerHTML = "<label>Campo texto</label>";
	
	var segundaDiv = document.createElement("div");
	segundaDiv.setAttribute("class", "col-md-6");	
	
	var terceiraDiv = document.createElement("div");
	terceiraDiv.setAttribute("class", "col-md-3");
	
	
	var inputField = document.createElement("input");
	inputField.setAttribute("class", "form-control textField");
	inputField.setAttribute("placeholder", "Entre com o atributo");
	
	var removeCampo = document.createElement("span");
	removeCampo.setAttribute("class" , "glyphicon glyphicon-remove-sign right");
	removeCampo.setAttribute("onclick", "removeElement('insertHere', 'id_"+i+"')");
	removeCampo.setAttribute("aria-hidden", "true");
	
	segundaDiv.appendChild(inputField);
	terceiraDiv.appendChild(removeCampo);
	
	divExterna.appendChild(primeiraDiv);
	divExterna.appendChild(segundaDiv);
	divExterna.appendChild(terceiraDiv);
	
	$('#'+parentDiv).append(divExterna);
	incrementI();
}

function addMultipleField (parentDiv, textField, typeField){
	var divExterna = document.createElement("div");
	divExterna.setAttribute("class", "up5");
	divExterna.setAttribute("id", "id_"+i);
	
	var subDiv1 = document.createElement("div");
	subDiv1.setAttribute("class","row up5");
	
	var primeiraDiv = document.createElement("div");
	primeiraDiv.setAttribute("class", "col-md-3");
	primeiraDiv.innerHTML = "<label>" + textField + "</label>";
	
	var segundaDiv = document.createElement("div");
	segundaDiv.setAttribute("class", "col-md-6");
	
	var terceiraDiv = document.createElement("div");
	terceiraDiv.setAttribute("class", "col-md-2");
	
	var quartaDiv = document.createElement("div");
	quartaDiv.setAttribute("class", "col-md-1");
	
	var inputField = document.createElement("input");
	inputField.setAttribute("class", "form-control " + typeField);
	inputField.setAttribute("placeholder", "Entre com o atributo");
	
	var button = document.createElement("button");
	button.setAttribute("class", "btn btn-sm btn-primary");
	button.setAttribute("onclick", "addItem('id_" + i + "')");
	button.setAttribute("type", "button");
	button.innerHTML = "+";

	var removeCampo = document.createElement("span");
	removeCampo.setAttribute("class" , "glyphicon glyphicon-remove-sign right");
	removeCampo.setAttribute("aria-hidden", "true");
	removeCampo.setAttribute("onclick", "removeElement('insertHere', 'id_"+i+"')");

	
	segundaDiv.appendChild(inputField);
	terceiraDiv.appendChild(button);	
	quartaDiv.appendChild(removeCampo);
	
	subDiv1.appendChild(primeiraDiv);
	subDiv1.appendChild(segundaDiv);
	subDiv1.appendChild(terceiraDiv);
	subDiv1.appendChild(quartaDiv);
	
	divExterna.appendChild(subDiv1);

	$('#'+parentDiv).append(divExterna);

	addItem("id_" + i);

	incrementI();
}




