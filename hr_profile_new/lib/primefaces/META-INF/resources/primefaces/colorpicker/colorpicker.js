PrimeFaces.widget.ColorPicker=function(b,a){this.id=b;this.cfg=a;this.jqId=PrimeFaces.escapeClientId(b);this.jqDialog=this.jqId+"_dialog";this.jqButton=this.jqId+"_button";PrimeFaces.widget.ColorPicker.superclass.constructor.call(this,b+"_cpContainer",this.cfg);this.on("rgbChange",this.selectColor);if(this.cfg.initialValue){this.setValue(this.cfg.initialValue,false)}this.setupUIControls()};YAHOO.lang.extend(PrimeFaces.widget.ColorPicker,YAHOO.widget.ColorPicker,{selectColor:function(b){var a=b.newValue,c=a[0]+","+a[1]+","+a[2];document.getElementById(this.id+"_input").value=c;jQuery(this.jqId+"_livePreview").css("backgroundColor","rgb("+c+")")},setupUIControls:function(){jQuery(this.jqDialog).dialog({autoOpen:false,resizable:false,draggable:false,height:230,width:400});var a=this;jQuery(this.jqButton).button().click(function(){if(jQuery(a.jqDialog).dialog("isOpen")){jQuery(a.jqDialog).dialog("close")}else{jQuery(a.jqDialog).dialog("open")}jQuery(a.jqDialog).parent().position({of:jQuery(this),my:"left top",at:"left bottom"})});jQuery(this.jqButton).mouseout(function(){jQuery(this).removeClass("ui-state-focus")})}});