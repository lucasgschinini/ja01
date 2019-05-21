	

	function contraer(identificador) {
			if($('#' + identificador).attr('class') == 'accordion-toggle less'){
				$('#' + identificador).removeClass('less');
				$('#' + identificador).addClass('more');
			}else{
				$('#' + identificador).removeClass('more');
				$('#' + identificador).addClass('less');
			}
		}