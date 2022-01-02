PrimeFaces.widget.AccordionPanel=function(c,b){this.id=c;this.cfg=b;this.jqId=PrimeFaces.escapeClientId(c);this.jq=jQuery(this.jqId+"_acco");this.stateHolder=jQuery(this.jqId+"_active");var a=this;this.jq.accordion(this.cfg);if(this.cfg.dynamic){this.markAsLoaded(this.jq.children("div").get(this.cfg.active))}this.jq.bind("accordionchangestart",function(d,e){a.onTabChange(d,e)})};PrimeFaces.widget.AccordionPanel.prototype.onTabChange=function(c,d){var a=d.newContent.get(0),b=this.cfg.dynamic&&!this.isLoaded(a);if(this.cfg.onTabChange){this.cfg.onTabChange.call(this,c,d)}this.stateHolder.val(d.options.active);if(b){this.loadDynamicTab(a)}else{if(this.cfg.ajaxTabChange){this.fireAjaxTabChangeEvent(a)}}};PrimeFaces.widget.AccordionPanel.prototype.loadDynamicTab=function(b){var a=this,c={source:this.id,process:this.id};c.update=this.cfg.ajaxTabChange?this.id+" "+this.cfg.onTabChangeUpdate:this.id;c.onsuccess=function(j){var g=j.documentElement,h=g.getElementsByTagName("update");for(var e=0;e<h.length;e++){var k=h[e].attributes.getNamedItem("id").nodeValue,f=h[e].firstChild.data;if(k==a.id){jQuery(b).html(f);if(a.cfg.cache){a.markAsLoaded(b)}}else{PrimeFaces.ajax.AjaxUtils.updateElement(k,f)}}return false};var d={};d[this.id+"_contentLoad"]=true;d[this.id+"_newTab"]=b.id;if(this.cfg.ajaxTabChange){d[this.id+"_tabChange"]=true}PrimeFaces.ajax.AjaxRequest(this.cfg.url,c,d)};PrimeFaces.widget.AccordionPanel.prototype.fireAjaxTabChangeEvent=function(a){var b={source:this.id,process:this.id};if(this.cfg.onTabChangeUpdate){b.update=this.cfg.onTabChangeUpdate}var c={};c[this.id+"_tabChange"]=true;c[this.id+"_newTab"]=a.id;PrimeFaces.ajax.AjaxRequest(this.cfg.url,b,c)};PrimeFaces.widget.AccordionPanel.prototype.markAsLoaded=function(a){jQuery(a).data("loaded",true)};PrimeFaces.widget.AccordionPanel.prototype.isLoaded=function(a){return jQuery(a).data("loaded")==true};PrimeFaces.widget.AccordionPanel.prototype.select=function(a){this.jq.accordion("activate",a)};PrimeFaces.widget.AccordionPanel.prototype.collapseAll=function(){this.jq.accordion("activate",false)};