package com.effects;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/*
 * @Author vishalbala_here 
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to Add Shadow effects on Buttons and MenuItems
 *  
 */
public class EffectsOnButtons {
	DropShadow drop = new DropShadow();
	Button button = new Button();
	AnchorPane pane = new AnchorPane();

	public EffectsOnButtons() {
		super();
		drop.setBlurType(BlurType.GAUSSIAN);
		drop.setColor(Color.rgb(63, 69, 64));
		drop.setHeight(100);
		drop.setWidth(150);
		drop.setOffsetX(2);
		drop.setOffsetY(10);
		drop.setSpread(0.1);
		drop.setRadius(20);
	}

	public void setShadowEffectOnButton(Button obj) {
		obj.setOnMouseEntered(eventOver);
		obj.setOnMouseExited(eventExit);
		this.button = obj;

	}
	
	public void setShadowEffectOnMenuItems(AnchorPane obj) {
		obj.setOnMouseEntered(eventOver);
		obj.setOnMouseExited(eventExit);
		this.pane = obj;

	}

	EventHandler<MouseEvent> eventOver = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			button.setEffect(drop);
			pane.setEffect(drop);

		}
	};

	EventHandler<MouseEvent> eventExit = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			button.setEffect(null);
			pane.setEffect(null);

		}
	};

}
