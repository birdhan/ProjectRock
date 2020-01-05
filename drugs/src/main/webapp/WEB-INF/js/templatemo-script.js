/*
 *	www.templatemo.com
 *******************************************************/

/* HTML document is loaded. DOM is ready. 
-----------------------------------------*/
$(document).ready(function(){

	/* Mobile menu */
	$('.mobile-menu-icon').click(function(){
		$('.templatemo-left-nav').slideToggle();				
	});

	/* Close the widget when clicked on close button */
	/*$('.shanhu').hide();*/
	$('.fa-times').click(function(){
		$(this).parent().slideUp(function(){
			$(this).hide();
		});
	});
	$('.dianji').click(function(){
		$('.shanhu').slideDown(function(){
			$('.shanhu').show();
		});
	});
});


