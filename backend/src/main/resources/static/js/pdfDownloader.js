
import html2pdf from 'html2pdf.js';


    document.getElementById('downloadPdfBtn').addEventListener('click', function () {
    const element = document.getElementById('showReportForm'); // ID del formulario que quieres convertir a PDF


    html2pdf(element);
});

