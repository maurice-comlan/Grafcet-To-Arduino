package AST;

import grafchart.sfc.GCDocument;
import grafchart.sfc.LabCommObject;
import grafchart.sfc.Utils;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;
import se.lth.control.orca.OrcaClient;
import se.lth.control.orca.OrcaClientChannel;

public class Program
  extends ASTNode<ASTNode>
  implements Cloneable
{
  public int version = 2013;
  public boolean input = true;
  public boolean output = true;
  public boolean orca = false;
  private ArrayList<LabCommEncoder> encoders = new ArrayList();
  public LabCommObject fcNode = null;
  
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Program clone()
    throws CloneNotSupportedException
  {
    Program localProgram = (Program)super.clone();
    localProgram.in$Circle(false);
    localProgram.is$Final(false);
    return localProgram;
  }
  
  public Program copy()
  {
    try
    {
      Program localProgram = clone();
      if (this.children != null) {
        localProgram.children = ((ASTNode[])this.children.clone());
      }
      return localProgram;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Program fullCopy()
  {
    Program localProgram = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localProgram.setChild(localASTNode, i);
    }
    return localProgram;
  }
  
  public void register(Socket paramSocket)
    throws IOException
  {
    LabCommEncoder localLabCommEncoder = null;
    LabCommDecoder localLabCommDecoder = null;
    if (root().output) {
      localLabCommEncoder = LabCommEncoder.Factory.newChannel(paramSocket.getOutputStream(), root().version);
    }
    if (root().input) {
      localLabCommDecoder = LabCommDecoder.Factory.newChannel(paramSocket.getInputStream(), root().version);
    }
    register(localLabCommEncoder, localLabCommDecoder);
  }
  
  public void register(OrcaClient paramOrcaClient)
    throws IOException
  {
    LabCommEncoder localLabCommEncoder = null;
    LabCommDecoder localLabCommDecoder = null;
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = getDecls().iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Decl)((Iterator)localObject1).next();
      if ((localObject2 instanceof SampleDecl)) {
        localArrayList.add(((Decl)localObject2).getName());
      }
    }
    localObject1 = (String[])localArrayList.toArray(new String[localArrayList.size()]);
    if (root().input)
    {
      localObject2 = paramOrcaClient.selectOutputTCP((String[])localObject1);
      if (localObject2 != null) {
        localLabCommDecoder = ((OrcaClientChannel)localObject2).getDecoder();
      }
    }
    if (root().output)
    {
      localObject2 = paramOrcaClient.selectInputTCP((String[])localObject1);
      if (localObject2 != null) {
        localLabCommEncoder = ((OrcaClientChannel)localObject2).getEncoder();
      }
    }
    register(localLabCommEncoder, localLabCommDecoder);
  }
  
  public void register(LabCommEncoder paramLabCommEncoder, LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    Iterator localIterator = getDecls().iterator();
    while (localIterator.hasNext())
    {
      Decl localDecl = (Decl)localIterator.next();
      localDecl.register(paramLabCommEncoder, paramLabCommDecoder);
    }
    if (paramLabCommEncoder != null) {
      synchronized (this.encoders)
      {
        this.encoders.add(paramLabCommEncoder);
      }
    }
    if (paramLabCommDecoder != null) {
      paramLabCommDecoder.start();
    }
  }
  
  public boolean hasReceived()
  {
    Iterator localIterator = getDecls().iterator();
    while (localIterator.hasNext())
    {
      Decl localDecl = (Decl)localIterator.next();
      if (localDecl.hasReceived()) {
        return true;
      }
    }
    return false;
  }
  
  public void send(String paramString)
  {
    Iterator localIterator;
    Decl localDecl;
    Object localObject1;
    if (paramString != null)
    {
      int i = 0;
      localIterator = getDecls().iterator();
      while (localIterator.hasNext())
      {
        localDecl = (Decl)localIterator.next();
        if ((localDecl instanceof SampleDecl))
        {
          localObject1 = (SampleDecl)localDecl;
          if (((SampleDecl)localObject1).getName().equals(paramString)) {
            i = 1;
          }
        }
      }
      if (i == 0) {
        Utils.writeInformation("Sample not found: " + paramString);
      }
    }
    synchronized (this.encoders)
    {
      localIterator = getDecls().iterator();
      while (localIterator.hasNext())
      {
        localDecl = (Decl)localIterator.next();
        localObject1 = this.encoders.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          LabCommEncoder localLabCommEncoder = (LabCommEncoder)((Iterator)localObject1).next();
          try
          {
            localDecl.send(localLabCommEncoder, paramString);
          }
          catch (SocketException localSocketException)
          {
            Utils.writeInformation(root().getName() + ": Receiver disconnected.");
            ((Iterator)localObject1).remove();
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
          }
        }
      }
      if (this.encoders.isEmpty()) {
        Utils.writeInformation(root().getName() + ": No receivers.");
      }
    }
  }
  
  public void jgCheck()
  {
    Iterator localIterator = getDecls().iterator();
    while (localIterator.hasNext())
    {
      Decl localDecl = (Decl)localIterator.next();
      if ((localDecl instanceof SampleDecl)) {
        return;
      }
    }
    error("No samples in specification: \"" + root().getName() + "\".");
  }
  
  public void generateGCDocument(GCDocument paramGCDocument)
  {
    generateGCDocument(paramGCDocument, null);
  }
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    Iterator localIterator = getDecls().iterator();
    while (localIterator.hasNext())
    {
      Decl localDecl = (Decl)localIterator.next();
      localDecl.generateGCDocument(paramGCDocument, paramString);
    }
  }
  
  public Program()
  {
    setChild(new List(), 0);
    is$Final(true);
  }
  
  public Program(List<Decl> paramList)
  {
    setChild(paramList, 0);
    is$Final(true);
  }
  
  protected int numChildren()
  {
    return 1;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setDeclList(List<Decl> paramList)
  {
    setChild(paramList, 0);
  }
  
  public int getNumDecl()
  {
    return getDeclList().getNumChild();
  }
  
  public Decl getDecl(int paramInt)
  {
    return (Decl)getDeclList().getChild(paramInt);
  }
  
  public void addDecl(Decl paramDecl)
  {
    List localList = (this.parent == null) || (state == null) ? getDeclListNoTransform() : getDeclList();
    localList.addChild(paramDecl);
  }
  
  public void addDeclNoTransform(Decl paramDecl)
  {
    List localList = getDeclListNoTransform();
    localList.addChild(paramDecl);
  }
  
  public void setDecl(Decl paramDecl, int paramInt)
  {
    List localList = getDeclList();
    localList.setChild(paramDecl, paramInt);
  }
  
  public List<Decl> getDecls()
  {
    return getDeclList();
  }
  
  public List<Decl> getDeclsNoTransform()
  {
    return getDeclListNoTransform();
  }
  
  public List<Decl> getDeclList()
  {
    List localList = (List)getChild(0);
    localList.getNumChild();
    return localList;
  }
  
  public List<Decl> getDeclListNoTransform()
  {
    return (List)getChildNoTransform(0);
  }
  
  public Program root()
  {
    ASTNode.State localState = state();
    Program localProgram = root_compute();
    return localProgram;
  }
  
  private Program root_compute()
  {
    return this;
  }
  
  public String getName()
  {
    ASTNode.State localState = state();
    String str = getName_compute();
    return str;
  }
  
  private String getName_compute()
  {
    return this.fcNode.getFullName();
  }
  
  public Program Define_Program_root(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    int i = getIndexOfChild(paramASTNode1);
    return this;
  }
  
  public String Define_String_lookupName(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    if (paramASTNode1 == getDeclListNoTransform())
    {
      int i = paramASTNode1.getIndexOfChild(paramASTNode2);
      for (int j = 0; j < i; j++)
      {
        String str = getDecl(j).getName();
        if (str.equals(paramString)) {
          return str;
        }
      }
      return null;
    }
    return getParent().Define_String_lookupName(this, paramASTNode1, paramString);
  }
  
  public TypeDecl Define_TypeDecl_lookupType(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    if (paramASTNode1 == getDeclListNoTransform())
    {
      int i = paramASTNode1.getIndexOfChild(paramASTNode2);
      for (int j = 0; j < i; j++)
      {
        Decl localDecl = getDecl(j);
        if (((localDecl instanceof TypeDecl)) && (localDecl.getName().equals(paramString))) {
          return (TypeDecl)localDecl;
        }
      }
      return null;
    }
    return getParent().Define_TypeDecl_lookupType(this, paramASTNode1, paramString);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/Program.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */