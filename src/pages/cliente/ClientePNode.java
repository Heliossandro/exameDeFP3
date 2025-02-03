package src.pages.cliente;

import java.io.IOException;
import java.io.RandomAccessFile;

import src.models.ClienteModelo;
import src.components.utils.*;

public class ClientePNode extends ClienteDadosTable implements SaveWriteReadInteface
{
  private ClientePNode next, prev;
  private ClienteModelo model;
  //protected int numeroAluno;
  
  public ClientePNode(ClienteModelo model)
  {
      super("clientes.DAT", 100);
      this.model = model;
      next = prev = null;
  } 
  public ClientePNode()
  { 
      model = new ClienteModelo();	
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

  public ClienteModelo getModel()
  {
      return model;
  }
  public void setPrev(ClientePNode prev)
  {
      this.prev = prev;
  }

  public void setNext(ClientePNode next)
  {
      this.next = next;
  }

  public ClientePNode getNext()
  {
      return next;
  }
  public ClientePNode getPrev()
  {
      return prev;
  }
  
  public void save()
  {
    adicionarNovoCadaver(this);
  }
 
  public static long sizeof()
  {		
      ClientePNode node = new ClientePNode();
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
