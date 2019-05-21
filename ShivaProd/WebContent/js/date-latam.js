/**
* This sorting plug-in for DataTables will correctly sort data in date time
* formats typically used in Latin America.
*
*  @name Date / time (dd/MM/yyyy HH:mm:ss)
*  @summary Sort date / time in this formats `dd/MM/yyyy`,`dd/MM/yyyy HH:mm`,`dd/MM/yyyy HH:mm:ss`. Basado la libreria date-de.js de
*  @author [Ronny Vedrilla](http://www.ambient-innovation.com)
*
*  @example
*    $('#example').dataTable( {
*       aoColumnDefs: [
*         { "sType": 'latam_date', "aTargets": [ 14 ] }
*       ]
*    } );
*    
 *  @example
*    $('#example').dataTable( {
*       aoColumnDefs: [
*         { "sType": 'latam_datetime', "aTargets": [ 14 ] }
*       ]
*    } );
*  
 *  @example
*    $('#example').dataTable( {
*       aoColumnDefs: [
*         { "sType": 'latam_datetime_seconds', "aTargets": [ 14 ] }
*       ]
*    } );
*/

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
             "latam_date-asc": function ( a, b ) {
                    var x, y;
                    if ($.trim(a) !== '-' && $.trim(a) !== '') {
                           var deDatea = $.trim(a).split(' ');
                           var deDatea2 = deDatea[0].split('/');
                           xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0]);
                           x = xDate.getTime();
                    } else {
//                           xDate = new Date(2100,1,1);
//                           x = xDate.getTime();
                    	x = Infinity;
                    }

                    if ($.trim(b) !== '-' && $.trim(b) !== '') {
                           var deDateb = $.trim(b).split(' ');
                           var deDateb2 = deDateb[0].split('/');
                           yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0]);
                           y = yDate.getTime();
                    } else {
//                           yDate = new Date(2100,1,1);
//                           y = yDate.getTime();
                    	y = Infinity;
                    }
                    
                    var z = ((x < y) ? -1 : ((x > y) ? 1 : 0));
                    return z;
             },

             "latam_date-desc": function ( a, b ) {
                    var x, y;
                    if ($.trim(a) !== '-' && $.trim(a) !== '') {
                           var deDatea = $.trim(a).split(' ');
                           var deDatea2 = deDatea[0].split('/');
                           xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0]);
                           x = xDate.getTime();
                    } else {
//                           x = 0;
                    	x = Infinity;
                    }

                    if ($.trim(b) !== '-' && $.trim(b) !== '') {
                           var deDateb = $.trim(b).split(' ');
                           var deDateb2 = deDateb[0].split('/');
                           yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0]);
                           y = yDate.getTime();
                    } else {
//                           y = 0;
                    	y = Infinity;
                    }
                    
                    var z = ((x < y) ? 1 : ((x > y) ? -1 : 0));
                    return z;
             },
       "latam_datetime-asc": function ( a, b ) {
                    var x, y;
                    if ($.trim(a) !== '-' && $.trim(a) !== '') {
                           var deDatea = $.trim(a).split(' ');
                           var deTimea = deDatea[1].split(':');
                           var deDatea2 = deDatea[0].split('/');
                           xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0],deTimea[0],deTimea[1]);
                           x = xDate.getTime();
                    } else {
//                           xDate = new Date(2100,1,1);
//                           x = xDate.getTime();
                    	x = Infinity;
                    }

                    if ($.trim(b) !== '-' && $.trim(b) !== '') {
                           var deDateb = $.trim(b).split(' ');
                           var deTimeb = deDateb[1].split(':');
                           var deDateb2 = deDateb[0].split('/');
                           yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0],deTimeb[0],deTimeb[1]);
                           y = yDate.getTime();
                    } else {
//                           yDate = new Date(2100,1,1);
//                           y = yDate.getTime();
                    	y = Infinity;
                    }
                    var z = ((x < y) ? -1 : ((x > y) ? 1 : 0));
                    return z;
             },

             "latam_datetime-desc": function ( a, b ) {
                    var x, y;
                    if ($.trim(a) !== '-' && $.trim(a) !== '') {
                           var deDatea = $.trim(a).split(' ');
                           var deTimea = deDatea[1].split(':');
                           var deDatea2 = deDatea[0].split('/');
                           xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0],deTimea[0],deTimea[1]);
                           x = xDate.getTime();
                    } else {
//                           x = 0;
                    	x = Infinity;
                    }

                    if ($.trim(b) !== '-' && $.trim(b) !== '') {
                           var deDateb = $.trim(b).split(' ');
                           var deTimeb = deDateb[1].split(':');
                           var deDateb2 = deDateb[0].split('/');
                           yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0],deTimeb[0],deTimeb[1]);
                           y = yDate.getTime();
                    } else {
//                           y = 0;
                    	y = Infinity;
                    }
                    var z = ((x < y) ? 1 : ((x > y) ? -1 : 0));
                    return z;
             },
       "latam_datetime_seconds-asc": function ( a, b ) {
             var x, y;
             if ($.trim(a) !== '-' && $.trim(a) !== '') {
                    var deDatea = $.trim(a).split(' ');
                    var deTimea = deDatea[1].split(':');
                    var deDatea2 = deDatea[0].split('/');
                    xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0],deTimea[0],deTimea[1],deTimea[2]);
                    x = xDate.getTime();
             } else {
//                    xDate = new Date(2100,1,1);
//                    x = xDate.getTime();
            	 x = Infinity;
             }

             if ($.trim(b) !== '-' && $.trim(b) !== '') {
                    var deDateb = $.trim(b).split(' ');
                    var deTimeb = deDateb[1].split(':');
                    var deDateb2 = deDateb[0].split('/');
                    yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0],deTimeb[0],deTimeb[1],deTimeb[2]);
                    y = yDate.getTime();
             } else {
//                    yDate = new Date(2100,1,1);
//                    y = yDate.getTime();
            	 y = Infinity;
             }
             var z = ((x < y) ? -1 : ((x > y) ? 1 : 0));
             return z;
       },

       "latam_datetime_seconds-desc": function ( a, b ) {
             var x, y;
             if ($.trim(a) !== '-' && $.trim(a) !== '') {
                    var deDatea = $.trim(a).split(' ');
                    var deTimea = deDatea[1].split(':');
                    var deDatea2 = deDatea[0].split('/');
                    xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0],deTimea[0],deTimea[1],deTimea[2]);
                    x = xDate.getTime();
             } else {
//                    x = 0;
            	 x = Infinity;
             }

             if ($.trim(b) !== '-' && $.trim(b) !== '') {
                    var deDateb = $.trim(b).split(' ');
                    var deTimeb = deDateb[1].split(':');
                    var deDateb2 = deDateb[0].split('/');
                    yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0],deTimeb[0],deTimeb[1],deTimeb[2]);
                    y = yDate.getTime();
             } else {
//                    y = 0;
            	 y = Infinity;
             }
             var z = ((x < y) ? 1 : ((x > y) ? -1 : 0));
             return z;
       },
       "latam_datetime_seconds_milliseconds-asc": function ( a, b ) {
           var x, y;
           if ($.trim(a) !== '-' && $.trim(a) !== '') {
                  var deDatea = $.trim(a).split(' ');
                  var deTimea = deDatea[1].split(':');
                  var deDatea2 = deDatea[0].split('/');
                  var deMillisecondsa = deTimea[2].split('.');
                  xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0],deTimea[0],deTimea[1],deMillisecondsa[0],deMillisecondsa[1]);
                  x = xDate.getTime();
           } else {
//                  xDate = new Date(2100,1,1);
//                  x = xDate.getTime();
          	 x = Infinity;
           }

           if ($.trim(b) !== '-' && $.trim(b) !== '') {
                  var deDateb = $.trim(b).split(' ');
                  var deTimeb = deDateb[1].split(':');
                  var deDateb2 = deDateb[0].split('/');
                  var deMillisecondsb = deTimeb[2].split('.');
                  yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0],deTimeb[0],deTimeb[1],deMillisecondsb[0],deMillisecondsb[1]);
                  y = yDate.getTime();
           } else {
//                  yDate = new Date(2100,1,1);
//                  y = yDate.getTime();
          	 y = Infinity;
           }
           var z = ((x < y) ? -1 : ((x > y) ? 1 : 0));
           return z;
     },

     "latam_datetime_seconds_milliseconds-desc": function ( a, b ) {
           var x, y;
           if ($.trim(a) !== '-' && $.trim(a) !== '') {
                  var deDatea = $.trim(a).split(' ');
                  var deTimea = deDatea[1].split(':');
                  var deDatea2 = deDatea[0].split('/');
                  var deMillisecondsa = deTimea[2].split('.');
                  xDate = new Date(deDatea2[2],deDatea2[1] - 1,deDatea2[0],deTimea[0],deTimea[1],deMillisecondsa[0],deMillisecondsa[1]);
                  x = xDate.getTime();
           } else {
//                  x = 0;
          	 x = Infinity;
           }

           if ($.trim(b) !== '-' && $.trim(b) !== '') {
                  var deDateb = $.trim(b).split(' ');
                  var deTimeb = deDateb[1].split(':');
                  var deDateb2 = deDateb[0].split('/');
                  var deMillisecondsb = deTimeb[2].split('.');
                  yDate = new Date(deDateb2[2],deDateb2[1] - 1,deDateb2[0],deTimeb[0],deTimeb[1],deMillisecondsb[0],deMillisecondsb[1]);
                  y = yDate.getTime();
           } else {
//                  y = 0;
          	 y = Infinity;
           }
           var z = ((x < y) ? 1 : ((x > y) ? -1 : 0));
           return z;
     }

} );