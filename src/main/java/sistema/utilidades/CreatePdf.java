package sistema.utilidades;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CreatePdf {

	PDDocument pdfdoc = new PDDocument();
	PDPage myPage = new PDPage();
	
	String nombre;
	String apellido;
	String plan;
	

	public CreatePdf() {

		pdfdoc.addPage(myPage);
	}

	public void generarPdf(int monto) throws IOException {

		try (PDPageContentStream cont = new PDPageContentStream(pdfdoc, myPage)) {

			cont.beginText();
			PDFont font = PDType1Font.TIMES_ROMAN;
			cont.setFont(font, 12);
			cont.setLeading(14.5f);

			cont.newLineAtOffset(25, 700);
			String line1 = "Titular: "+nombre;
			cont.showText(line1);

			cont.newLine();

			String line2 = "Apellido: "+apellido;
			cont.showText(line2);
			cont.newLine();

			String line4 = "Monto: "+monto;
			cont.showText(line4);
			cont.newLine();
			
			String line5 = "0 788492-808274 ";
			cont.showText(line5);
			cont.newLine();
			
			cont.endText();
		}
		String directorio = System.getProperty("user.dir") + "\\cupon.pdf";
		System.out.println("CreadO");
		pdfdoc.save(directorio);

		pdfdoc.close();
	}
	
	public void getNombre(String nombre){
		this.nombre = nombre;
	}
	public void getApellido(String apellido){
		this.apellido = apellido;
	}
	public void getPlan(String plan){
		this.plan = plan;
	}
	
	

}