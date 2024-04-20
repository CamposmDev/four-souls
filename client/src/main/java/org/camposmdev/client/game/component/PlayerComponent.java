package org.camposmdev.client.game.component;

import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {
  public LifeComponent life;

  @Override
  public void onAdded() {
    System.out.println("player comp");
  }

  @Override
  public void onUpdate(double tpf) {

  }
}
