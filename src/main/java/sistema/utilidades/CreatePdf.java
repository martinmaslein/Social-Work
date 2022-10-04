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

	public CreatePdf() {

		pdfdoc.addPage(myPage);
	}

	public void generarPdf() throws IOException {

		try (PDPageContentStream cont = new PDPageContentStream(pdfdoc, myPage)) {

			cont.beginText();
			PDFont font = PDType1Font.TIMES_ROMAN;
			cont.setFont(font, 12);
			cont.setLeading(14.5f);

			cont.newLineAtOffset(25, 700);
			String line1 = "Titular: ";
			cont.showText(line1);

			cont.newLine();

			String line2 = "Apellido:";
			cont.showText(line2);
			cont.newLine();

			String line3 = "Plan";
			cont.showText(line3);
			cont.newLine();

			cont.endText();
		}
		String directorio = System.getProperty("user.dir") + "\\cupon.pdf";

		pdfdoc.save(directorio);

		System.out.println("PDF created");

		pdfdoc.close();
	}

}