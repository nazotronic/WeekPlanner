/*
 * Project: WeekPlanner
 *
 * Author: Vereshchynskyi Nazar
 * Email: verechnazar12@gmail.com
 * Version: 1.0.0
 * Date: 28.05.2025
 */

package App.Scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class AScene extends Scene {
	public AScene(Parent parent, double v, double v1) {
		super(parent, v, v1);
	}
	
	public void fillScene() {
		
	}
}
