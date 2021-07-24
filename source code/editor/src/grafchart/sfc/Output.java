package grafchart.sfc;

import com.nwoods.jgo.JGoStroke;
import java.io.Serializable;

public class Output
  implements Serializable
{
  public GCStepOutPort port = null;
  public JGoStroke line = null;
  
  public Output(MacroStep paramMacroStep)
  {
    this.line = new JGoStroke();
    this.line.addPoint(10, 0);
    this.line.addPoint(10, 5);
    this.line.setSelectable(false);
    this.port = new GCStepOutPort();
    paramMacroStep.addObjectAtTail(this.line);
    paramMacroStep.addObjectAtTail(this.port);
  }
  
  public Output(JGoStroke paramJGoStroke, GCStepOutPort paramGCStepOutPort)
  {
    this.line = paramJGoStroke;
    this.port = paramGCStepOutPort;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Output.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */