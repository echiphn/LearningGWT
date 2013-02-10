package com.manning.gwtip.bookstore.client.dnd;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LoadListener;
import com.google.gwt.user.client.ui.Widget;

public class ReflectedImage extends Image{
    public ReflectedImage(){
        super();
        addLoadListener( new LoadListener(){
            public void onLoad(Widget widget) {
                reflect( getElement(), (float) 0.25, (float) 0.5, 188, 150 );
            }

            public void onError(Widget widget) {
            }
            
        });
    }
    
    public native void unreflect( Element image )/*-{
        if (image.className == "reflected") {
                image.className = image.parentNode.className;
                image.parentNode.parentNode.replaceChild(image, image.parentNode);
        }
    }-*/;
    
    public native void reflect(Element image, float height, float opacity, int offsetHeight, int offsetWidth)/*-{
			var d = document.createElement('div');
			var p = image;
			
			var classes = p.className.split(' ');
			var newClasses = '';
			for (j=0;j<classes.length;j++) {
				if (classes[j] != "reflect") {
					if (newClasses) {
						newClasses += ' '
					}
					
					newClasses += classes[j];
				}
			}

			var reflectionHeight = Math.floor(offsetHeight*height);
			var divHeight = Math.floor(p.height*(1+height));
			
			var reflectionWidth = offsetWidth;
			
			if (document.all && !window.opera) {
				 d.className = newClasses;
				p.className = 'reflected';
				
				d.style.cssText = p.style.cssText;
				p.style.cssText = 'vertical-align: bottom';
			
				var reflection = document.createElement('img');
				reflection.src = p.src;
				reflection.style.width = reflectionWidth+'px';
				
				reflection.style.marginBottom = "-"+(p.height-reflectionHeight)+'px';
				reflection.style.filter = 'flipv progid:DXImageTransform.Microsoft.Alpha(opacity='+(opacity*100)+', style=1, finishOpacity=0, startx=0, starty=0, finishx=0, finishy='+(height*100)+')';
				
				d.style.width = reflectionWidth+'px';
				d.style.height = divHeight+'px';
				p.parentNode.replaceChild(d, p);
				
				d.appendChild(p);
				d.appendChild(reflection);
			} else {
                                var canvas = document.createElement('canvas');
				if (canvas.getContext) {
					d.className = newClasses;
					p.className = 'reflected';
					
					d.style.cssText = p.style.cssText;
					p.style.cssText = 'vertical-align: bottom';
			
					var context = canvas.getContext("2d");
                                        
					canvas.style.height = reflectionHeight+'px';
                                        canvas.style.width = reflectionWidth+'px';
					canvas.height = reflectionHeight;
					canvas.width = reflectionWidth;
					
					d.style.width = reflectionWidth+'px';
					d.style.height = divHeight+'px';
					p.parentNode.replaceChild(d, p);
					
					d.appendChild(p);
                                        d.appendChild(canvas);
					
					context.save();
					
					context.translate(0,image.height-1);
					context.scale(1,-1);
					
					context.drawImage(image, 0, 0, reflectionWidth, offsetHeight);
	
					context.restore();
					
					context.globalCompositeOperation = "destination-out";
					var gradient = context.createLinearGradient(0, 0, 0, reflectionHeight);
					
					gradient.addColorStop(1, "rgba(255, 255, 255, 1.0)");
					gradient.addColorStop(0, "rgba(255, 255, 255, "+(1-opacity)+")");
		
					context.fillStyle = gradient;
					if (navigator.appVersion.indexOf('WebKit') != -1) {
						context.fill();
					} else {
						context.fillRect(0, 0, reflectionWidth, reflectionHeight*2);
					}
				}
			}
    }-*/;
    
    /*protected void onDetach() {
        super.onDetach();
        unreflect( this.getElement() );
    }

    protected void onAttach() {
        super.onAttach();
        Window.alert("Reflecting");
        reflect( getElement(), (float) 0.25, (float) 0.5, getOffsetHeight(), getOffsetWidth() );
        
    }*/
   
    
    
   
    
}
