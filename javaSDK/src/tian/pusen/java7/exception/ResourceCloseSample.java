package tian.pusen.java7.exception;

public class ResourceCloseSample implements AutoCloseable {

	@Override
	public void close() throws Exception {
		System.out.println("Close resource");
	}
	
	public void use() throws Exception{
		try (ResourceCloseSample resource = new ResourceCloseSample())  {
			System.out.println("使用资源。");
		}
	}
	public static void main(String[] args) {
		try {
			new ResourceCloseSample().use();
		} catch (Exception ex) {
			if(ex != null)
				System.out.println("Main Exception");
			ex.printStackTrace();
		}
	}
}
