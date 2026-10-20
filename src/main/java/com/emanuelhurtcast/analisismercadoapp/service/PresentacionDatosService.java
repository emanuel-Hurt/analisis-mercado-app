package com.emanuelhurtcast.analisismercadoapp.service;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;
import tech.tablesaw.api.Table;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PresentacionDatosService {

    /**
     * Obtiene únicamente las primeras 10 filas de los datos cargados.
     */
    public List<Map<String, Object>> obtenerPrimerasDiezFilas(Table datosTable) {
        Table primerasDiez = datosTable.first(10); // Límite de 10 filas
        List<Map<String, Object>> filas = new ArrayList<>();

        for (int i = 0; i < primerasDiez.rowCount(); i++) {
            Map<String, Object> fila = new HashMap<>();
            for (String colName : primerasDiez.columnNames()) {
                fila.put(colName, primerasDiez.column(colName).get(i));
            }
            filas.add(fila);
        }
        return filas;
    }

    /**
     * Calcula media, mediana y desviación estándar de una columna numérica.
     */
    public Map<String, Double> calcularEstadisticosDescriptivos(Table datosTable, String nombreColumna) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        double[] valores = datosTable.numberColumn(nombreColumna).asDoubleArray();
        for (double v : valores) {
            stats.addValue(v);
        }

        Map<String, Double> resultados = new HashMap<>();
        resultados.put("media", stats.getMean());
        resultados.put("mediana", stats.getPercentile(50));
        resultados.put("desviacionEstandar", stats.getStandardDeviation());
        return resultados;
    }

    /**
     * Calcula la distribución de frecuencia absoluta y relativa.
     */
    public List<Map<String, Object>> calcularDistribucionFrecuencias(Table datosTable, String nombreColumna) {
        Table conteo = datosTable.countBy(nombreColumna);
        long totalRegistros = datosTable.rowCount();

        List<Map<String, Object>> frecuencias = new ArrayList<>();
        for (int i = 0; i < conteo.rowCount(); i++) {
            Map<String, Object> fila = new HashMap<>();
            String categoria = conteo.stringColumn(0).get(i);
            int abs = conteo.intColumn("Count").get(i);
            double rel = (double) abs / totalRegistros;

            fila.put("categoria", categoria);
            fila.put("frecuenciaAbsoluta", abs);
            fila.put("frecuenciaRelativa", rel);
            fila.put("porcentaje", rel * 100);
            frecuencias.add(fila);
        }
        return frecuencias;
    }

    /**
     * Genera una tabla de contingencia cruzada entre dos variables categóricas.
     */
    public Map<String, Object> generarTablaContingenciaCruzada(Table datosTable, String colVar1, String colVar2) {
        Table xTab = datosTable.xTabCounts(colVar1, colVar2);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("columnas", xTab.columnNames());

        List<Map<String, Object>> filas = new ArrayList<>();
        for (int i = 0; i < xTab.rowCount(); i++) {
            Map<String, Object> fila = new HashMap<>();
            for (String col : xTab.columnNames()) {
                fila.put(col, xTab.column(col).get(i));
            }
            filas.add(fila);
        }
        resultado.put("filas", filas);
        return resultado;
    }
}
