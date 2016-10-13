 $(document).ready(function() {
   $('.agrega_pup').click( function() {
    $('.agrega_pup span').toggle();
    $('.agrega_pup2 span').hide();
    $("agrega_pup").addClass("selecto");
	$("a.set_goal").removeClass("boton_inactivo");
    });
     $('.agrega_pup2').click( function() {
    $('.agrega_pup2 span').toggle();
    $('.agrega_pup span').hide();
    });
});