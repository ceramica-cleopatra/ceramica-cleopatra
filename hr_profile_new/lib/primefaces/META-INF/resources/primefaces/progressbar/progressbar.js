PrimeFaces.widget.ProgressBar=function(b,a){this.id=b;this.cfg=a;this.jqId=PrimeFaces.escapeClientId(b);jQuery(this.jqId).progressbar(this.cfg)};PrimeFaces.widget.ProgressBar.prototype.setValue=function(a){jQuery(this.jqId).progressbar("value",a)};PrimeFaces.widget.ProgressBar.prototype.getValue=function(){return jQuery(this.jqId).progressbar("value")};PrimeFaces.widget.ProgressBar.prototype.start=function(){var a=this;if(this.cfg.ajax){this.progressPoll=setInterval(function(){var b={source:a.id,process:a.id,formId:a.cfg._formId,async:true,oncomplete:function(f,c,d){var e=d[a.id+"_value"];a.setValue(e);if(e===100){a.fireCompleteEvent()}}};PrimeFaces.ajax.AjaxRequest(a.cfg.url,b)},this.cfg.interval)}};PrimeFaces.widget.ProgressBar.prototype.fireCompleteEvent=function(){clearInterval(this.progressPoll);var a={source:this.id,process:this.id,formId:this.cfg.formId,async:true};if(this.cfg.onCompleteUpdate){a.update=this.cfg.onCompleteUpdate}if(this.cfg.oncomplete){a.oncomplete=this.cfg.oncomplete}var b={};b[this.id+"_complete"]=true;PrimeFaces.ajax.AjaxRequest(this.cfg.url,a,b)};PrimeFaces.widget.ProgressBar.prototype.cancel=function(){clearInterval(this.progressPoll);var a=this;var b={source:this.id,process:this.id,formId:this.cfg.formId,async:true,oncomplete:function(f,d,e){a.setValue(0)}};if(this.cfg.onCancelUpdate){b.update=this.cfg.onCancelUpdate}var c={};c[this.id+"_cancel"]=true;PrimeFaces.ajax.AjaxRequest(this.cfg.url,b,c)};