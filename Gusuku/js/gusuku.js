	var normalColor = "transparent";
	var hoverColor = "#EEEEEE";
	function mout(obj) {
		obj.style.backgroundColor = normalColor; 
	}
	function mover(obj) {
		obj.style.backgroundColor = hoverColor;
	}
	
	function swapVisibility(elementId, anchorElement) {
			var style = document.getElementById(elementId).style;
			if (style.display == 'none') {
				style.display = 'block';
				anchorElement.innerHTML = '[-]';
			} else {
				style.display = 'none';
				anchorElement.innerHTML = '[+]';
			}
		}
		function hideFoldings() {
			var i = 1;
			var element = document.getElementById('project_' + i);
			while (i < 10) {
			if(element){
				element.style.display = 'none';
				}
				element = document.getElementById('project_' + ++i);
			}
		}