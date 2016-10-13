	$(document).ready(function(){	

		if (!$.browser.opera) {
    
			// select element styling
			$('select.select').each(function(){
				var title = $(this).attr('title');
				if( $('option:selected', this).val() != ''  ) title = $('option:selected',this).text();
				$(this)
					.css({'z-index':10,'opacity':0,'-khtml-appearance':'none'})
					.after('<span class="select"><em>' + title + '</em></span>')
					.change(function(){
						val = $('option:selected',this).text();
						$(this).siblings("span.select").html("<em>"+val+"</em>");
						$(this).blur();
						})
			});

		};
		
	});


