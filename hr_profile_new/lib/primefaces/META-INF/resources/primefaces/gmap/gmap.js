PrimeFaces.widget.GMap=function(b,a){this.id=b;this.cfg=a;this.map=new google.maps.Map(document.getElementById(this.id),this.cfg);if(this.cfg.markers){this.configureMarkers()}if(this.cfg.polylines){this.configurePolylines()}if(this.cfg.polygons){this.configurePolygons()}this.configureEventListeners()};PrimeFaces.widget.GMap.prototype.getMap=function(){return this.map};PrimeFaces.widget.GMap.prototype.getInfoWindow=function(){return this.cfg.infoWindow};PrimeFaces.widget.GMap.prototype.openWindow=function(a){this.getInfoWindow().open(this.getMap(),a)};PrimeFaces.widget.GMap.prototype.configureMarkers=function(){var a=this;for(var c=0;c<this.cfg.markers.length;c++){var b=this.cfg.markers[c];b.setMap(this.map);if(this.cfg.hasOverlaySelectListener){this.addOverlaySelectListener(b)}if(this.cfg.hasMarkerDragListener){google.maps.event.addListener(b,"dragend",function(d){a.fireMarkerDragEvent(d,this)})}}};PrimeFaces.widget.GMap.prototype.fireMarkerDragEvent=function(c,a){var b={source:this.id,process:this.id,formId:this.cfg.formId};if(this.cfg.onMarkerDragUpdate){b.update=this.cfg.onMarkerDragUpdate}var d={};d[this.id+"_markerDragged"]=true;d[this.id+"_markerId"]=a.id;d[this.id+"_lat"]=c.latLng.lat();d[this.id+"_lng"]=c.latLng.lng();PrimeFaces.ajax.AjaxRequest(this.cfg.url,b,d)};PrimeFaces.widget.GMap.prototype.configurePolylines=function(){this.addOverlays(this.cfg.polylines)};PrimeFaces.widget.GMap.prototype.configurePolygons=function(){this.addOverlays(this.cfg.polygons)};PrimeFaces.widget.GMap.prototype.addOverlaySelectListener=function(b){var a=this;google.maps.event.addListener(b,"click",function(c){a.fireOverlaySelectEvent(c,this)})};PrimeFaces.widget.GMap.prototype.fireOverlaySelectEvent=function(e,c){var a=this,b={source:this.id,process:this.id,formId:this.cfg.formId};var f={};f[this.id+"_overlaySelected"]=true;f[this.id+"_overlayId"]=c.id;if(this.cfg.onOverlaySelectStart){b.onstart=this.cfg.onOverlaySelectStart}if(this.cfg.onOverlaySelectComplete){b.oncomplete=this.cfg.onOverlaySelectComplete}if(this.cfg.infoWindow){var d=this.getInfoWindow();b.update=d.id;b.onsuccess=function(l){var j=l.documentElement,k=j.getElementsByTagName("update");for(var g=0;g<k.length;g++){var m=k[g].attributes.getNamedItem("id").nodeValue,h=k[g].firstChild.data;if(m==d.id){d.setContent(h);a.openWindow(c)}else{PrimeFaces.ajax.AjaxUtils.updateElement(m,h)}}return false}}else{if(this.cfg.onOverlaySelectUpdate){b.update=this.cfg.onOverlaySelectUpdate}}PrimeFaces.ajax.AjaxRequest(this.cfg.url,b,f)};PrimeFaces.widget.GMap.prototype.configureEventListeners=function(){var a=this;if(this.cfg.onPointClick){google.maps.event.addListener(this.map,"click",function(b){a.cfg.onPointClick(b)})}if(this.cfg.hasStateChangeListener){this.configureStateChangeListener()}if(this.cfg.hasPointSelectListener){this.configurePointSelectListener()}};PrimeFaces.widget.GMap.prototype.configureStateChangeListener=function(){var a=this,b=function(){a.fireStateChangeEvent()};google.maps.event.addListener(this.map,"zoom_changed",b);google.maps.event.addListener(this.map,"dragend",b)};PrimeFaces.widget.GMap.prototype.fireStateChangeEvent=function(){var a={source:this.id,process:this.id,formId:this.cfg.formId};if(this.cfg.onStateChangeUpdate){a.update=this.cfg.onStateChangeUpdate}var b={};b[this.id+"_stateChanged"]=true;b[this.id+"_northeast"]=this.map.getBounds().getNorthEast().lat()+","+this.map.getBounds().getNorthEast().lng();b[this.id+"_southwest"]=this.map.getBounds().getSouthWest().lat()+","+this.map.getBounds().getSouthWest().lng();b[this.id+"_center"]=this.map.getBounds().getCenter().lat()+","+this.map.getBounds().getCenter().lng();b[this.id+"_zoom"]=this.map.getZoom();PrimeFaces.ajax.AjaxRequest(this.cfg.url,a,b)};PrimeFaces.widget.GMap.prototype.configurePointSelectListener=function(){var a=this;google.maps.event.addListener(this.map,"click",function(b){a.firePointSelectEvent(b)})};PrimeFaces.widget.GMap.prototype.firePointSelectEvent=function(b){var a={source:this.id,process:this.id,formId:this.cfg.formId};if(this.cfg.onPointSelectUpdate){a.update=this.cfg.onPointSelectUpdate}var c={};c[this.id+"_pointSelected"]=true;c[this.id+"_pointLatLng"]=b.latLng.lat()+","+b.latLng.lng();PrimeFaces.ajax.AjaxRequest(this.cfg.url,a,c)};PrimeFaces.widget.GMap.prototype.addOverlay=function(a){a.setMap(this.map)};PrimeFaces.widget.GMap.prototype.addOverlays=function(b){for(var a=0;a<b.length;a++){b[a].setMap(this.map);if(this.cfg.hasOverlaySelectListener){this.addOverlaySelectListener(b[a])}}};PrimeFaces.widget.GMap.prototype.checkResize=function(){google.maps.event.trigger(this.map,"resize");this.map.setZoom(this.map.getZoom())};