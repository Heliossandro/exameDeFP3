
  import java.io.IOException;
  import java.io.RandomAccessFile;

import src.components.utils.SaveWriteReadInteface;
import src.models.ClienteModelo;
  
  public class ClientesPNode extends ClientesDadosTable implements SaveWriteReadInteface
  {
    private ClientesPNode next, prev;
    private ClienteModelo model;
    //protected int numeroAluno;
    
	public ClientesPNode(ClienteModelo model)
    {
		super("ClientesES.DAT", 100);
		this.model = model;
		next = prev = null;
    } 
    public ClientesPNode()
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
    public void setPrev(ClientesPNode prev)
    {
		this.prev = prev;
    }

    public void setNext(ClientesPNode next)
    {
		this.next = next;
    }

    public ClientesPNode getNext()
    {
		return next;
    }
    public ClientesPNode getPrev()
    {
		return prev;
    }
	
    public void save()
    {
		adicionarNovoCliente(this);
    }
   
    public static long sizeof()
    {		
		ClientesPNode node = new ClientesPNode();
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
