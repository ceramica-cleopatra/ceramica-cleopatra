PrimeFaces.widget.DataGrid=function(b,a){this.id=b;this.cfg=a;this.jqId=PrimeFaces.escapeClientId(this.id);this.content=this.jqId+"_content";if(this.cfg.paginator){this.setupPaginator()}};PrimeFaces.widget.DataGrid.prototype.setupPaginator=function(){var a=this.getPaginator();a.subscribe("changeRequest",this.handlePagination,null,this);a.render()};PrimeFaces.widget.DataGrid.prototype.handlePagination=function(b){var c={};c[this.id+"_ajaxPaging"]=true;c[this.id+"_first"]=b.recordOffset;c[this.id+"_rows"]=b.rowsPerPage;c[this.id+"_page"]=b.page;var a=this;PrimeFaces.ajax.AjaxRequest(this.cfg.url,{source:this.id,update:this.id,process:this.id,formId:this.cfg.formId,onsuccess:function(j){var g=j.documentElement,h=g.getElementsByTagName("update");for(var d=0;d<h.length;d++){var k=h[d].attributes.getNamedItem("id").nodeValue,f=h[d].firstChild.data;if(k==a.id){if(a.cfg.effect){var e=f;jQuery(a.content).fadeOut(a.cfg.effectSpeed,function(){jQuery(a.content).html(e);jQuery(a.content).fadeIn(a.cfg.effectSpeed)})}else{jQuery(a.content).html(f)}a.getPaginator().setState(b)}else{PrimeFaces.ajax.AjaxUtils.updateElement(k,f)}}return false}},c)};PrimeFaces.widget.DataGrid.prototype.getPaginator=function(){return this.cfg.paginator};