package sistema.utilidades;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class CreatePdf {

	PDDocument pdfdoc = new PDDocument();
	PDPage myPage = new PDPage();

	String nombre;
	String apellido;
	String plan;

	public CreatePdf() {

		pdfdoc.addPage(myPage);
	}

	public void generarPdf(int monto, int familiares) throws IOException {

		try (PDPageContentStream cont = new PDPageContentStream(pdfdoc, myPage)) {

			cont.beginText();
			PDFont font = PDType1Font.TIMES_ROMAN;
			cont.setFont(font, 14);
			cont.setLeading(14.5f);

			cont.newLineAtOffset(25, 700);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String line0 = "Fecha Cupón: " + dtf.format(now)
					+ "                                                                                        ObraSocial";
			cont.showText(line0);
			cont.newLine();
			cont.newLine();
			cont.newLine();
			
			
			cont.setFont(font, 18);
			String lineT = "                                                       Cupón de Pago";
			cont.showText(lineT);
			cont.newLine();
			cont.newLine();
			cont.newLine();
			
			cont.setFont(font, 14);
			String line1 = "Nombre:                                                            " + nombre+" "+ apellido;
			cont.showText(line1);
			cont.newLine();
			cont.newLine();

			String line2 = "Cantidad de Afiliados:                                             " + familiares;
			cont.showText(line2);
			cont.newLine();
			cont.newLine();

			String line4 = "Monto total a pagar:                                              $" + monto;
			cont.showText(line4);
			cont.newLine();
			cont.newLine();

			cont.endText();
			
			PDImageXObject pdImage = PDImageXObject.createFromFile("./img/codigo-de-barras.png",pdfdoc);
		    cont.drawImage(pdImage, 220, 400, 200, 120);
		}
		String directorio = System.getProperty("user.dir") + "\\cupon.pdf";
		System.out.println("CreadO");
		pdfdoc.save(directorio);

		pdfdoc.close();
	}

	public void getNombre(String nombre) {
		this.nombre = nombre;
	}

	public void getApellido(String apellido) {
		this.apellido = apellido;
	}

	public void getPlan(String plan) {
		this.plan = plan;
	}

}