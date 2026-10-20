package com.emanuelhurtcast.analisismercadoapp.service;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;
import tech.tablesaw.aggregate.CrossTab;
import tech.tablesaw.api.Table;

import java.util.*;

@Service
public class PresentacionDinamicaService {

    public Object procesarOperacion(Table datosTable, String operacion, String col1, String col2) {
        if (datosTable == null) {
            throw new IllegalStateException("No hay datos cargados en el sistema.");
        }

        switch (operacion.toLowerCase()) {
            case "media":
            case "mediana":
            case "desviacion":
                return calcularEstadisticoUnivariado(datosTable, col1, operacion);
            case "frecuencia_absoluta":
            case "frecuencia_relativa":
                return calcularFrecuencias(datosTable, col1);
            case "contingencia":
                return generarTablaContingencia(datosTable, col1, col2);
            case "grafico":
                return prepararDatosGrafico(datosTable, col1);
            default:
                throw new IllegalArgumentException("Operación no válida: " + operacion);
        }
    }

    private Map<String, Object> calcularEstadisticoUnivariado(Table table, String col, String op) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        double[] vals = table.numberColumn(col).asDoubleArray();
        for (double v : vals) stats.addValue(v);

        Map<String, Object> res = new HashMap<>();
        res.put("tipo", "valor");
        double val = 0.0;
        String titulo = "";

        switch (op) {
            case "media":
                val = stats.getMean();
                titulo = "Media de la columna: " + col;
                break;
            case "mediana":
                val = stats.getPercentile(50);
                titulo = "Mediana de la columna: " + col;
                break;
            case "desviacion":
                val = stats.getStandardDeviation();
                titulo = "Desviación Estándar de la columna: " + col;
                break;
        }
        res.put("titulo", titulo);
        res.put("resultado", val);

        return res;
    }

    private Map<String, Object> calcularFrecuencias(Table table, String col) {
        Table conteo = table.countBy(col);
        long total = table.rowCount();
        List<Map<String, Object>> filas = new ArrayList<>();

        for (int i = 0; i < conteo.rowCount(); i++) {
            Map<String, Object> row = new HashMap<>();
            String categoria = conteo.stringColumn(0).get(i);
            int abs = conteo.intColumn("Count").get(i);
            double rel = (double) abs / total;

            row.put("categoria", categoria);
            row.put("frecuenciaAbsoluta", abs);
            row.put("frecuenciaRelativa", rel);
            row.put("porcentaje", rel * 100);
            filas.add(row);
        }

        Map<String, Object> res = new HashMap<>();
        res.put("tipo", "frecuencia");
        res.put("titulo", "Distribución de Frecuencias para: " + col);
        res.put("filas", filas);
        return res;
    }

    private Map<String, Object> generarTablaContingencia(Table table, String c1, String c2) {
        // Corrección para Tablesaw v0.44.4 usando la clase CrossTab
        Table xTab = CrossTab.counts(table, table.stringColumn(c1), table.stringColumn(c2));

        Map<String, Object> res = new HashMap<>();
        res.put("tipo", "contingencia");
        res.put("titulo", "Tabla de Contingencia Cruzada (" + c1 + " vs " + c2 + ")");
        res.put("columnas", xTab.columnNames());

        List<Map<String, Object>> filas = new ArrayList<>();
        for (int i = 0; i < xTab.rowCount(); i++) {
            Map<String, Object> fila = new HashMap<>();
            for (String col : xTab.columnNames()) {
                fila.put(col, xTab.column(col).get(i));
            }
            filas.add(fila);
        }
        res.put("filas", filas);
        return res;
    }

    private Map<String, Object> prepararDatosGrafico(Table table, String col) {
        Table conteo = table.countBy(col);
        List<String> categorias = conteo.stringColumn(0).asList();
        List<Integer> frecuencias = conteo.intColumn("Count").asList();

        Map<String, Object> res = new HashMap<>();
        res.put("tipo", "grafico");
        res.put("titulo", "Gráfico Estadístico de Distribución: " + col);
        res.put("categorias", categorias);
        res.put("frecuencias", frecuencias);
        return res;
    }
}