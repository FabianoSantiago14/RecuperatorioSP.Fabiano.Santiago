/*
 * Click
 */
package recuperatoriosp.fabiano.santiago;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static void guardarInventario(List<Producto> productos) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw = new FileWriter("productos.json");

        fw.write(gson.toJson(productos));
        fw.close();

    }

    public static List<Producto> cargarInventario() throws IOException {
        File file = new File("productos.json");
        if (!file.exists()) {
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(file));

        
        JsonArray arr = JsonParser.parseReader(br).getAsJsonArray();
        List<Producto> lista = new ArrayList<>();

        for (JsonElement elem : arr) {
            JsonObject o = elem.getAsJsonObject();
            String tipo = o.get("tipo").getAsString();

            String nombre = o.get("nombre").getAsString();
            String conc = o.get("concentracion").getAsString();
            LocalDate venc = LocalDate.parse(o.get("vencimiento").getAsString());

            if (tipo.equals("quimico")) {
                String adv = o.get("advertencia").getAsString();
                lista.add(new ProductoQuimico(nombre, conc, venc, adv));
            } else {
                String et = o.get("etiqueta").getAsString();
                lista.add(new ProductoEcologico(nombre, conc, venc, et));
            }
        }

        return lista;
    }

}
