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
  
  public boolean isEmptyNode() {
    return getKey().trim().isEmpty();
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
  
  public void save() {
    if (!isEmptyNode()) {
        adicionarNovoCliente(this);
    } else {
        System.out.println("Não foi possível salvar: Cliente inválido.");
    }
}

 
  public static long sizeof() 
  {
    return new ClienteModelo().sizeof();
  }

  @Override
  public String toString() {
      return "Cliente: " + model.getNome() + ", Telefone: " + model.getNumTelefone();
  }  
}
