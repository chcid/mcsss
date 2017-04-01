function menuClick(id) {
	$("#menuUl > li").removeClass("active");
	$("#li" + id).addClass("active");
}
/**
* Purpose: blink a page element
* Preconditions: the element you want to apply the blink to,
the number of times to blink the element (or -1 for infinite times),
the speed of the blink
**/
function blink(elem, times, speed)
{
if (times > 0 || times < 0) {
  if ($(elem).hasClass("blink"))
     $(elem).removeClass("blink");
  else
     $(elem).addClass("blink");
 }

 clearTimeout(function() { blink(elem, times, speed); });

 if (times > 0 || times < 0) {
   setTimeout(function() { blink(elem, times, speed); }, speed);
   times-= .5;
 }
}
/*
 * $(document).ready(function() {
 * $('[data-toggle="confirmation-popout"]').confirmation({ popout : true });
 * $('#delete').confirmation({ popout : true }); });
 */

/*
 * !function($) {
 * 
 * $(function() {
 * 
 * $('[data-toggle="confirmation"]').confirmation();
 * $('[data-toggle="confirmation-singleton"]').confirmation({ singleton : true
 * }); $('[data-toggle="confirmation-popout"]').confirmation({ popout : true });
 * alert("hi");
 *  })
 * 
 * }(window.jQuery)
 */