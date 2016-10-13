// ver 1.1.1
// 09-04-03
/*
(c) Copyrights 2009

jQuery Plugin by Ryoma Nakashima
nakashima@2next.co.jp
http://www.2next.co.jp/

Project's sites: 
http://code.google.com/p/js-autocomplete/

License: same as jQuery license. 

USAGE:
	with jQuery Hotkeys Plugin
	http://code.google.com/p/js-hotkeys/


	// usage
	
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/jquery.hotkeys.js" type="text/javascript"></script>
	<script src="js/jquery.auto-complete.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		autoComplete();
	});
	</script>



	<input type="text" class="auto-complete">
	<ul>
		<li>aaa</li>
		<li>bbb</li>
		<li>ccc</li>
	</li>
    
Note:


*/

//	document.body.scrollHeight;

function autoComplete (){


	// Config
	var conf = new Array();
	conf['box-background'] = '#FFF';
	conf['box-border'] = 'solid 1px #000';
	conf['box-max-height'] = '350'; // ! number only

	conf['list-padding'] = '1px 5px';
	conf['list-font-style'] = 'normal';
	conf['list-font-weight'] = 'normal';
	conf['list-font-size'] = '12px';
	conf['list-line-height'] = '20px';

	conf['list-color'] = '#000';
	conf['list-background'] = '#FFF';

	conf['list-active-color'] = '#FFF';
	conf['list-active-background'] = '#3399FF';



	$('.auto-complete').each(function(){
		var active = -1;
		var keybind = 0;

		var ua = $.browser;
	
		var textBox = $(this);
		var box = $(this).next();
		var list = box.find('li');
	
		var width = Number(textBox.width()) + Number(textBox.css('padding-left').replace('px','')) + Number(textBox.css('padding-right').replace('px',''));
		var height = textBox.height();

		textBox.attr('autocomplete','off');

		box.css('display','none');
		box.css('background',conf['box-background']);
		box.css('border',conf['box-border']);
		box.css('z-index','2147483647');
		box.css('overflow-x','hidden');
		box.width(width+2);

		list.css('padding',conf['list-padding']);
		list.css('overflow-x','hidden');
		list.width(width-8);
		list.height(20);
		list.css('font-style',conf['list-font-style']);
		list.css('font-weight',conf['list-font-weight']);
		list.css('font-size',conf['list-font-size']);
		list.css('line-height',conf['list-line-height']);
		list.css('white-space','nowrap');

		list.each(function(){
			$(this).data('text',$(this).text().replace(/\s+$/,''));
			$(this).data('textLC',$(this).data('text').toLowerCase());
		});
	
		textBox.click(function(){
			actionBox();
		});
		textBox.keypress(function(){
			actionBox();
		});
		textBox.keyup(function(){
			actionBox();
		});
	
		function actionBox () {
			if(!textBox.val() && list.size() > 0){
				list.each(function(){
					$(this).css('display','block');
					$(this).addClass('select-list');
				});
				selectBoxOn();
			}else{
				var boxon = 0;
				var input = textBox.val();
				var inputLC = textBox.val().toLowerCase();
				
				list.each(function(){
					if(!$(this).data('textLC').match(inputLC) || $(this).data('text') == input){
						$(this).css('display','none');
						$(this).removeClass('select-list');
					}else{
						$(this).css('display','block');
						$(this).addClass('select-list');
						boxon = 1;
					}
				});
				if(boxon){
					selectBoxOn();
				}else{
					selectBoxOff();
				}
			}
		}
		function selectBoxOn () {			
			if(box.css('display') != 'block'){
				active = -1;
			}

			var offset = textBox.offset();
			var left = offset.left;
			var top = offset.top;
			
			box.css('position','absolute');


			box.css('left',left + 'px');
			if(ua.msie || ua.opera || ua.safari){
				box.css('top',(top+height+2) + 'px');
			}else{
				box.css('top',(top+height) + 'px');
			}

			box.css('display','block');
			
			if(box.height() > conf['box-max-height']){
				box.css('max-height',conf['box-max-height'] + 'px');
				box.css('overflow-y','scroll');
			}else{
				box.css('overflow-y','auto');
			}
			$(textBox).bind('blur',selectBoxOff);
			
			box.mouseover(function(){
				$(textBox).unbind('blur',selectBoxOff);
			});
			box.mouseout(function(){
				$(textBox).bind('blur',selectBoxOff);
			});

			list.bind('mouseover',listMouseOver);
			list.bind('click',listClick);

			$(document).bind('keydown', 'down', listDown);
			$(document).bind('keydown', 'up', listUp);
			$(document).bind('keydown', 'return', listEnter);
			keybind = 1;
		}
		function selectBoxOff () {		
			box.css('display','none');
			list.unbind('mouseover',listMouseOver);
			list.unbind('click',listClick);
			if(keybind){
				$(document).unbind('keydown', 'down', listDown);
				$(document).unbind('keydown', 'up', listUp);
				$(document).unbind('keydown', 'return', listEnter);
				keybind = 0;
			}

			box.scrollTop(0);
			list.css('background',conf['list-background']);
			list.css('color',conf['list-color']);
		}
		function listMouseOver () {
			list.css('background',conf['list-background']);
			list.css('color',conf['list-color']);
			$(this).css('background',conf['list-active-background']);
			$(this).css('color',conf['list-active-color']);
			active = box.find('li.select-list').index(this);
		}
		function listClick () {
			var text = $(this).text().replace(/\s+$/,'');
			textBox.val(text);
			selectBoxOff();
			textBox.focus();
		}
		function listDown () {
			if(box.find('li.select-list').eq(active + 1).size()){
				list.css('background',conf['list-background']);
				list.css('color',conf['list-color']);
				active += 1;
				box.find('li.select-list').eq(active).css('background',conf['list-active-background']);
				box.find('li.select-list').eq(active).css('color',conf['list-active-color']);
				
				scrollTopNew = active * list.outerHeight() - (conf['box-max-height'] - list.outerHeight());
				
				if(box.scrollTop() - scrollTopNew < list.height()){
					box.scrollTop(scrollTopNew);
				}				

				return true;
			}
		}
		function listUp () {
			if(box.find('li.select-list').eq(active - 1).size()){
				list.css('background',conf['list-background']);
				list.css('color',conf['list-color']);
				active -= 1;
				box.find('li.select-list').eq(active).css('background',conf['list-active-background']);
				box.find('li.select-list').eq(active).css('color',conf['list-active-color']);

				scrollTopNew = active * list.outerHeight();
				if(scrollTopNew - box.scrollTop() < list.height()){
					box.scrollTop(scrollTopNew);
				}				

				return true;
			}
		}
		function listEnter (event) {
			var text = box.find('li.select-list').eq(active).text().replace(/\s+$/,'');
			textBox.val(text);
			selectBoxOff();
			textBox.focus();
			enableEnter(event);
		}
		function enableEnter(e){
			if(e.srcElement){
				o = e.srcElement;
			}else{
				o = e.target;
			}
			if (o.tagName != 'TEXTAREA' && e.keyCode == 13) {
				if(e.preventDefault){
					e.preventDefault();
					e.stopPropagation();
				}
				e.returnValue=false;
				e.cancelBubble=true;
			}
		}
	});

}
