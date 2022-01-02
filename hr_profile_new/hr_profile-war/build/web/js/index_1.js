var timeOut;
$(document).ready(function() {
  	var $auth = $('.auth');
  	var $overlay = $('.overlay');
  	var $loginButton = $('.btn');
        var $backButton = $('.bottom-button');
    

  
 $loginButton.click(function(){      $(this).toggleClass('loading');       $auth.removeClass('opa');      
$backButton.removeClass('back');                         
  timeOut=setTimeout(function() {
    $auth.toggleClass('close');
    $overlay.toggleClass('open');
    $menu.fadeIn(200);
    $('.diamond').fadeIn(200);
   },50000);
});
  
  var $s = $('.s');
	var $line = $('.line');
  var $menu = $('.menu');


  $backButton.click(function() {
    $(this).addClass('back');
    $s.removeClass('spread');
    $line.removeClass('open');
    $overlay.removeClass('open');
    $auth.addClass('opa');   
    $auth.removeClass('close'); 
	$loginButton.removeClass('loading');
    $('.nav-menu').removeClass('open');
  });
  
  
  $menu.click(function() {
    $line.toggleClass('open');
    $s.toggleClass('spread');
    $('.nav-menu').toggleClass('open');
  });
});