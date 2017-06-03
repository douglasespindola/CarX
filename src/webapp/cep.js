function getCidadeByCep() {
	var req = new XMLHttpRequest();
	var cep = document.getElementById("cep");
	var cidade = document.getElementById("cidade");
	var estado = document.getElementById("estado");
	req.open("GET", "http://localhost:8080/rest/api/ceps/" + cep.value, true);
	req.onreadystatechange = function() {
		if (req.readyState === XMLHttpRequest.DONE && req.status === 200) {
			var cidadeObj = JSON.parse(req.responseText);
			cidade.innerHTML = cidadeObj.nome;
			estado.innerHTML = cidadeObj.estado.nome;
		}
	}
	req.send();
}

function cadastrarCep() {
	var req = new XMLHttpRequest();
	var cep = document.getElementById("cep");
	var cidade = document.getElementById("cidade");
	var estado = document.getElementById("estado");
	var mensagem = document.getElementById("mensagem");
	var estadoValue = estado.options[estado.selectedIndex].value;
	var estadoText = estado.options[estado.selectedIndex].text;
	var obj = {
		"nome" : cidade.value,
		"estado" : {
			"nome" : estadoText,
			"sigla" : estadoValue
		},
		"prefixoCep" : cep.value
	};
	req.open("POST", "http://localhost:8080/rest/api/ceps/", true);
	req.setRequestHeader("Content-Type", "application/json");
	req.onreadystatechange = function() {
		if (req.readyState === XMLHttpRequest.DONE && req.status === 200) {
			mensagem.innerHTML = req.responseText;
		}
	}
	req.send(JSON.stringify(obj));
}