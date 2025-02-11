package src.pages.fornecedor;

import java.io.IOException;
import java.io.RandomAccessFile;

import src.components.utils.SaveWriteReadInteface;

public class FornecedoresPNode extends FornecedoresDadosTable implements SaveWriteReadInteface
{
  private FornecedoresPNode next, prev;
  private FornecedorModelo model;
  //protected int numeroAluno;
  
public FornecedoresPNode(FornecedorModelo model)
  {
  super("fornecedoes.DAT", 100);
  this.model = model;
  next = prev = null;
  } 
  public FornecedoresPNode()
  { 
  model = new FornecedorModelo();	
  }
  
  public String getKey()
  {
  return model.getNome();
  }
  
  public boolean isEmptyNode()
  {
  return ( getKey().equalsIgnoreCase("") == true );
  }

  public void write(RandomAccessFile stream) throws IOException
  {
  //stream.writeInt(codigoAluno);
  //stream.writeBoolean(eliminado);		
  model.write(stream);
  }
  
  public void read(RandomAccessFile stream) throws IOException
  {
  //codigoAluno = stream.readInt();	
  //eliminado = stream.readBoolean();		
  model.read(stream);
  }	

  public FornecedorModelo getModel()
  {
  return model;
  }
  public void setPrev(FornecedoresPNode prev)
  {
  this.prev = prev;
  }

  public void setNext(FornecedoresPNode next)
  {
  this.next = next;
  }

  public FornecedoresPNode getNext()
  {
  return next;
  }
  public FornecedoresPNode getPrev()
  {
  return prev;
  }

  public void save()
  {
  adicionarNovoFornecedor(this);
  }
 
  public static long sizeof()
  {		
  FornecedoresPNode node = new FornecedoresPNode();
  try
      { 
          return  node.model.sizeof();
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return 0;
   }	
   public String toString()
   {
  return model.toString();
   }
}
