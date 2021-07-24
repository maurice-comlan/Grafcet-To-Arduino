package grafchart.sfc;

import com.nwoods.jgo.JGoStroke;
import java.io.Serializable;

public class Input
  implements Serializable
{
  public GCStepInPort port = null;
  public JGoStroke line = null;
  
  public Input(MacroStep paramMacroStep)
  {
    this.line = new JGoStroke();
    this.line.addPoint(10, 0);
    this.line.addPoint(10, 5);
    this.line.setSelectable(false);
    this.port = new GCStepInPort();
    paramMacroStep.addObjectAtTail(this.line);
    paramMacroStep.addObjectAtTail(this.port);
  }
  
  public Input(JGoStroke paramJGoStroke, GCStepInPort paramGCStepInPort)
  {
    this.line = paramJGoStroke;
    this.port = paramGCStepInPort;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Input.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */