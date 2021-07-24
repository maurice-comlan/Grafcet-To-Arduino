package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoView;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GCLink
  extends JGoLink
{
  private static JGoPen wideLinkPen = new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F));
  private String strokePointTag = new String("Stroke");
  private static boolean linkModification = true;
  
  public GCLink(JGoPort paramJGoPort1, JGoPort paramJGoPort2)
  {
    super(paramJGoPort1, paramJGoPort2);
    setOrthogonal(true);
    setSelectable(true);
    setResizable(true);
    setGrabChildSelection(true);
  }
  
  public GCLink() {}
  
  public static void setLinkModification(boolean paramBoolean)
  {
    linkModification = paramBoolean;
  }
  
  public Rectangle handleResize(Graphics2D paramGraphics2D, JGoView paramJGoView, Rectangle paramRectangle, Point paramPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setManuallyAdjusted(true);
    return super.handleResize(paramGraphics2D, paramJGoView, paramRectangle, paramPoint, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public JGoObject copyObject(JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    GCLink localGCLink = (GCLink)super.copyObject(paramJGoCopyEnvironment);
    if (localGCLink != null) {
      localGCLink.setManuallyAdjusted(getManuallyAdjusted());
    }
    return localGCLink;
  }
  
  public void setWide()
  {
    setPen(wideLinkPen);
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    Document localDocument = paramElement.getOwnerDocument();
    paramElement.setAttribute("manAdj", getManuallyAdjusted() ? "1" : "0");
    JGoPort fromJGoPort = getFromPort();
    JGoArea localJGoArea = fromJGoPort.getParent();
    int i = paramVector2.indexOf(localJGoArea);
    Object localObject1;
    Object localObject2;
    Iterator localIterator;
    Object localObject3;
    if ((i >= 0) && (i < paramVector1.size()))
    {
      localObject1 = (String)paramVector1.get(i);
      if (localObject1 != null)
      {
        if ((localJGoArea instanceof ParallelSplit))
        {
          localObject2 = (ParallelSplit)localJGoArea;
          if (fromJGoPort.equals(((ParallelSplit)localObject2).myOutPort1)) {
            localObject1 = new String((String)localObject1 + "L");
          } else if (fromJGoPort.equals(((ParallelSplit)localObject2).myOutPort2)) {
            localObject1 = new String((String)localObject1 + "R");
          }
        }
        if ((localJGoArea instanceof MacroStep))
        {
          localObject2 = (MacroStep)localJGoArea;
          localIterator = ((MacroStep)localObject2).outputPorts.iterator();
          while (localIterator.hasNext())
          {
            localObject3 = (Output)localIterator.next();
            if (fromJGoPort.equals(((Output)localObject3).port)) {
              localObject1 = new String((String)localObject1 + "_" + ((MacroStep)localObject2).outputPorts.indexOf(localObject3));
            }
          }
          if (fromJGoPort.equals(((MacroStep)localObject2).myExcOutPort)) {
            localObject1 = new String((String)localObject1 + "E");
          }
        }
        paramElement.setAttribute("fromObject", (String)localObject1);
      }
    }
    localJGoArea = getToPort().getParent();
    i = paramVector2.indexOf(localJGoArea);
    if ((i >= 0) && (i < paramVector1.size()))
    {
      localObject1 = (String)paramVector1.get(i);
      if (localObject1 != null)
      {
        if ((localJGoArea instanceof ParallelJoin))
        {
          localObject2 = (ParallelJoin)localJGoArea;
          if (getToPort().equals(((ParallelJoin)localObject2).myInPort1)) {
            localObject1 = new String((String)localObject1 + "L");
          } else if (getToPort().equals(((ParallelJoin)localObject2).myInPort2)) {
            localObject1 = new String((String)localObject1 + "R");
          }
        }
        if ((localJGoArea instanceof MacroStep))
        {
          localObject2 = (MacroStep)localJGoArea;
          localIterator = ((MacroStep)localObject2).inputPorts.iterator();
          while (localIterator.hasNext())
          {
            localObject3 = (Input)localIterator.next();
            if (getToPort().equals(((Input)localObject3).port)) {
              localObject1 = new String((String)localObject1 + "_" + ((MacroStep)localObject2).inputPorts.indexOf(localObject3));
            }
          }
          if (getToPort().equals(((MacroStep)localObject2).myHistoryPort)) {
            localObject1 = new String((String)localObject1 + "H");
          }
        }
        paramElement.setAttribute("toObject", (String)localObject1);
      }
    }
    int j = getNumPoints();
    for (int k = 0; k < j; k++)
    {
      localObject1 = localDocument.createElement(this.strokePointTag);
      ((Element)localObject1).setAttribute("x", Integer.toString(getPointX(k)));
      ((Element)localObject1).setAttribute("y", Integer.toString(getPointY(k)));
      paramElement.appendChild((Node)localObject1);
    }
    return paramElement;
  }
  
  public static GCLink loadXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    GCLink localGCLink = null;
    String str1 = paramElement.getAttribute("toObject");
    String str2 = new String(str1.toString());
    if ((str1.endsWith("R")) || (str1.endsWith("L")) || (str1.endsWith("N")) || (str1.endsWith("H"))) {
      str2 = str1.substring(0, str1.length() - 1);
    }
    int i = str1.indexOf('_');
    String str3 = "";
    int j = 0;
    if (i != -1)
    {
      str2 = str1.substring(0, i);
      str3 = str1.substring(i + 1);
      j = Integer.parseInt(str3);
    }
    int k = paramVector1.indexOf(str2);
    Object localObject4;
    if ((k >= 0) && (k < paramVector2.size()))
    {
        Object localObject3 = paramVector2.get(k);
      if ((localObject3 instanceof GCStep)) {
        localObject2 = ((GCStep)localObject3).getInPort();
      }
      if ((localObject3 instanceof ConnectionPostIn)) {
        localObject2 = ((ConnectionPostIn)localObject3).myPort;
      }
      if (((localObject3 instanceof MacroStep)) && (!(localObject3 instanceof ProcedureStep)))
      {
        localObject4 = (MacroStep)localObject3;
        if (str1.endsWith("H")) {
          localObject2 = ((MacroStep)localObject4).myHistoryPort;
        } else if (str1.indexOf('_') != -1) {
          localObject2 = ((Input)((MacroStep)localObject4).inputPorts.get(j)).port;
        } else {
          localObject2 = ((Input)((MacroStep)localObject4).inputPorts.get(0)).port;
        }
      }
      if ((localObject3 instanceof ProcedureStep)) {
        localObject2 = ((ProcedureStep)localObject3).getInPort();
      }
      if ((localObject3 instanceof GCTransition)) {
        localObject2 = ((GCTransition)localObject3).getInPort();
      }
      if ((localObject3 instanceof ExceptionTransition)) {
        localObject2 = ((ExceptionTransition)localObject3).getInPort();
      }
      if ((localObject3 instanceof ParallelJoin)) {
        if (str1.endsWith("L")) {
          localObject2 = ((ParallelJoin)localObject3).myInPort1;
        } else if (str1.endsWith("R")) {
          localObject2 = ((ParallelJoin)localObject3).myInPort2;
        }
      }
      if ((localObject3 instanceof ParallelSplit)) {
        localObject2 = ((ParallelSplit)localObject3).myInPort;
      }
    }
    Object localObject3 = paramElement.getAttribute("fromObject");
    str2 = new String(((String)localObject3).toString());
    if ((((String)localObject3).endsWith("R")) || (((String)localObject3).endsWith("L")) || (((String)localObject3).endsWith("N")) || (((String)localObject3).endsWith("E"))) {
      str2 = ((String)localObject3).substring(0, ((String)localObject3).length() - 1);
    }
    i = ((String)localObject3).indexOf('_');
    str3 = "";
    j = 0;
    if (i != -1)
    {
      str2 = ((String)localObject3).substring(0, i);
      str3 = ((String)localObject3).substring(i + 1);
      j = Integer.parseInt(str3);
    }
    k = paramVector1.indexOf(str2);
    if ((k >= 0) && (k < paramVector2.size()))
    {
      localObject4 = paramVector2.get(k);
      if ((localObject4 instanceof GCStep)) {
        localObject1 = ((GCStep)localObject4).getOutPort();
      }
      if ((localObject4 instanceof ConnectionPostOut)) {
        localObject1 = ((ConnectionPostOut)localObject4).myPort;
      }
      if (((localObject4 instanceof MacroStep)) && (!(localObject4 instanceof ProcedureStep)))
      {
        MacroStep localMacroStep = (MacroStep)localObject4;
        if ((localObject2 instanceof ExceptionTransitionInPort)) {
          localObject1 = localMacroStep.getExceptionOutPort();
        } else if (((String)localObject3).indexOf('_') != -1) {
          localObject1 = ((Output)localMacroStep.outputPorts.get(j)).port;
        } else {
          localObject1 = ((Output)localMacroStep.outputPorts.get(0)).port;
        }
      }
      if ((localObject4 instanceof ProcedureStep)) {
        if ((localObject2 instanceof ExceptionTransitionInPort)) {
          localObject1 = ((ProcedureStep)localObject4).getExceptionOutPort();
        } else {
          localObject1 = ((ProcedureStep)localObject4).getOutPort();
        }
      }
      if ((localObject4 instanceof GCTransition)) {
        localObject1 = ((GCTransition)localObject4).getOutPort();
      }
      if ((localObject4 instanceof ExceptionTransition)) {
        localObject1 = ((ExceptionTransition)localObject4).getOutPort();
      }
      if ((localObject4 instanceof ParallelSplit)) {
        if (((String)localObject3).endsWith("L")) {
          localObject1 = ((ParallelSplit)localObject4).myOutPort1;
        } else if (((String)localObject3).endsWith("R")) {
          localObject1 = ((ParallelSplit)localObject4).myOutPort2;
        }
      }
      if ((localObject4 instanceof ParallelJoin)) {
        localObject1 = ((ParallelJoin)localObject4).myOutPort;
      }
    }
    if ((localObject1 != null) && (localObject2 != null))
    {
      localGCLink = new GCLink((JGoPort)localObject1, (JGoPort)localObject2);
      localObject4 = paramElement.getElementsByTagName("Stroke");
      localGCLink.removeAllPoints();
      for (int m = 0; m < ((NodeList)localObject4).getLength(); m++)
      {
        Node localNode = ((NodeList)localObject4).item(m);
        if (localNode.getNodeType() == 1)
        {
          Element localElement = (Element)localNode;
          int n = Integer.parseInt(localElement.getAttribute("x"));
          int i1 = Integer.parseInt(localElement.getAttribute("y"));
          localGCLink.insertPoint(m, n, i1);
        }
      }
      if (((localObject1 instanceof GCStepExceptionOutPort)) || ((localObject2 instanceof GCStepHistoryInPort))) {
        localGCLink.setWide();
      }
      if (paramElement.hasAttribute("manAdj"))
      {
        String str4 = paramElement.getAttribute("manAdj");
        if (str4.equals("1")) {
          localGCLink.setManuallyAdjusted(true);
        }
      }
    }
    else
    {
      Utils.writeError("Ignored invalid GCLink from \"" + (String)localObject3 + "\" to \"" + str1 + "\".");
    }
    return localGCLink;
  }
  
  private boolean getManuallyAdjusted()
  {
    return getAdjustingStyle() == 3;
  }
  
  private void setManuallyAdjusted(boolean paramBoolean)
  {
    if (paramBoolean) {
      setAdjustingStyle(3);
    } else {
      setAdjustingStyle(0);
    }
  }
  
  public void calculateStroke()
  {
    super.calculateStroke();
    if ((linkModification) && (!getManuallyAdjusted()) && (getFromPort() != null) && (getToPort() != null) && (!(getToPort().getParent() instanceof ExceptionTransition)) && (!(getFromPort().getParent() instanceof ExceptionTransition)) && (!(getToPort() instanceof GCStepHistoryInPort)))
    {
      Point localPoint1 = getFromPort().getLocation();
      Point localPoint2 = getToPort().getLocation();
      GCDocument localGCDocument = (GCDocument)getFromPort().getDocument();
      if ((localPoint1.getY() > localPoint2.getY()) && (getNumPoints() == 6))
      {
        if (objectHit(2, localGCDocument))
        {
          moveSegment(2, 10, true);
          while (objectHit(3, localGCDocument))
          {
            moveSegment(3, 10, true);
            while (objectHit(4, localGCDocument)) {
              moveSegment(4, -10, true);
            }
          }
        }
        if (objectHit(3, localGCDocument))
        {
          moveSegment(3, 10, true);
          while (objectHit(2, localGCDocument))
          {
            moveSegment(2, 10, true);
            while (objectHit(4, localGCDocument)) {
              moveSegment(4, -10, true);
            }
          }
        }
        if (objectHit(4, localGCDocument))
        {
          moveSegment(4, -10, true);
          while (objectHit(3, localGCDocument))
          {
            moveSegment(3, 10, true);
            while (objectHit(2, localGCDocument)) {
              moveSegment(2, 10, true);
            }
          }
        }
      }
      if ((localPoint1.getY() > localPoint2.getY()) && (getNumPoints() == 6))
      {
        if (objectHit(2, localGCDocument))
        {
          moveSegment(2, 10, false);
          while (objectHit(3, localGCDocument))
          {
            moveSegment(3, 10, false);
            while (objectHit(4, localGCDocument)) {
              moveSegment(4, 10, false);
            }
          }
        }
        if (objectHit(3, localGCDocument))
        {
          moveSegment(3, 10, false);
          while (objectHit(2, localGCDocument))
          {
            moveSegment(2, 10, false);
            while (objectHit(4, localGCDocument)) {
              moveSegment(4, 10, false);
            }
          }
        }
        if (objectHit(4, localGCDocument))
        {
          moveSegment(4, 10, false);
          while (objectHit(3, localGCDocument))
          {
            moveSegment(3, 10, false);
            while (objectHit(2, localGCDocument)) {
              moveSegment(2, 10, false);
            }
          }
        }
      }
    }
  }
  
  protected void moveSegment(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (paramInt1 == 2)
      {
        setPoint(1, getPointX(1), getPointY(1) + paramInt2);
        setPoint(2, getPointX(2), getPointY(2) + paramInt2);
      }
      if (paramInt1 == 3)
      {
        setPoint(2, getPointX(2) + paramInt2, getPointY(2));
        setPoint(3, getPointX(3) + paramInt2, getPointY(3));
      }
      if (paramInt1 == 4)
      {
        setPoint(3, getPointX(3), getPointY(3) + paramInt2);
        setPoint(4, getPointX(4), getPointY(4) + paramInt2);
      }
    }
  }
  
  protected boolean crossVertical(int paramInt1, int paramInt2, int paramInt3, Rectangle paramRectangle)
  {
    int i = (paramInt1 >= paramRectangle.getX()) && (paramInt1 <= paramRectangle.getX() + paramRectangle.getWidth()) ? 1 : 0;
    int j = (paramRectangle.getY() + paramRectangle.getHeight() >= paramInt2) && (paramRectangle.getY() <= paramInt3) ? 1 : 0;
    return (i != 0) && (j != 0);
  }
  
  protected boolean crossHorizontal(int paramInt1, int paramInt2, int paramInt3, Rectangle paramRectangle)
  {
    int i = (paramInt1 >= paramRectangle.getY()) && (paramInt1 <= paramRectangle.getY() + paramRectangle.getHeight()) ? 1 : 0;
    int j = (paramRectangle.getX() + paramRectangle.getWidth() >= paramInt2) && (paramRectangle.getX() <= paramInt3) ? 1 : 0;
    return (i != 0) && (j != 0);
  }
  
  protected boolean objectHit(int paramInt, GCDocument paramGCDocument)
  {
    boolean bool = false;
    JGoListPosition localJGoListPosition = paramGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null) && (!bool); localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition))
    {
      if ((!(localJGoObject instanceof JGoLink)) && (!(localJGoObject instanceof GCGroup)))
      {
        int i;
        int j;
        if (paramInt == 2)
        {
          i = getPointX(1);
          j = getPointX(2);
          bool = crossHorizontal(getPointY(1), Math.min(i, j), Math.max(i, j), localJGoObject.getBoundingRect());
        }
        else if (paramInt == 3)
        {
          bool = crossVertical(getPointX(2), getPointY(3), getPointY(2), localJGoObject.getBoundingRect());
        }
        else if (paramInt == 4)
        {
          i = getPointX(3);
          j = getPointX(4);
          bool = crossHorizontal(getPointY(3), Math.min(i, j), Math.max(i, j), localJGoObject.getBoundingRect());
        }
      }
      localJGoListPosition = paramGCDocument.getNextObjectPos(localJGoListPosition);
    }
    return bool;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCLink.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */