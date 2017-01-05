import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Picture {

	private long index;
	private String name;
	private JSONObject tags;
	private long greyavg;
	private double[] dhashdist;
	private long[] color1;
	private String dhash;
	private double[] ahashdist;
	private long[] color2;
	private String ahash;
	private String phash;
	private long[] date;
	private double[] phashdist;
	private long note;
	private long id;
	private long[] size;

	public Picture(int index) {
		String photoFileName = "./data/json/info-photo.json";

		JSONParser parser = new JSONParser();
		try {
			FileReader file = new FileReader(photoFileName);
			Object obj = parser.parse(file);
			file.close();
			JSONArray jsonArray = (JSONArray) obj;
			JSONObject pictureObj = (JSONObject) jsonArray.get(index);

			this.index = (long) pictureObj.get("index");
			this.name = (String) pictureObj.get("name");
			this.tags = (JSONObject) pictureObj.get("tags");
			this.greyavg = (long) pictureObj.get("greyavg");
			this.ahash = (String) pictureObj.get("ahash");
			this.phash = (String) pictureObj.get("phash");
			this.dhash = (String) pictureObj.get("dhash");

			JSONArray ahashdistArray = (JSONArray) pictureObj.get("ahashdist");
			JSONArray dhashdistArray = (JSONArray) pictureObj.get("dhashdist");
			JSONArray phashdistArray = (JSONArray) pictureObj.get("phashdist");

			this.ahashdist = new double[55];
			this.phashdist = new double[55];
			this.dhashdist = new double[55];

			for (int i = 0; i < 55; i++) {
				this.ahashdist[i] = (double) ahashdistArray.get(i);
				this.phashdist[i] = (double) dhashdistArray.get(i);
				this.dhashdist[i] = (double) phashdistArray.get(i);
			}

			JSONObject dateObj = (JSONObject) pictureObj.get("date");
			this.date = new long[6];
			this.date[0] = (long) dateObj.get("year");
			this.date[1] = (long) dateObj.get("month");
			this.date[2] = (long) dateObj.get("day");
			this.date[3] = (long) dateObj.get("hour");
			this.date[4] = (long) dateObj.get("min");
			this.date[5] = (long) dateObj.get("sec");

			this.note = (long) pictureObj.get("note");
			this.id = (long) pictureObj.get("id");

			JSONObject color1Obj = (JSONObject) pictureObj.get("color1");
			this.color1 = new long[3];
			this.color1[0] = (long) color1Obj.get("r");
			this.color1[1] = (long) color1Obj.get("g");
			this.color1[2] = (long) color1Obj.get("b");
			
			JSONObject color2Obj = (JSONObject) pictureObj.get("color2");
			this.color2 = new long[3];
			this.color2[0] = (long) color2Obj.get("r");
			this.color2[1] = (long) color2Obj.get("g");
			this.color2[2] = (long) color2Obj.get("b");
			
			JSONObject sizeObj = (JSONObject) pictureObj.get("size");
			this.size = new long[2];
			this.size[0] = (long) sizeObj.get("width");
			this.size[1] = (long) sizeObj.get("height");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public long index() {
		return this.index;
	}

	public String name() {
		return this.name;
	}

	public JSONObject tags() {
		return this.tags;
	}

	public long greyavg() {
		return this.greyavg;
	}

	public double dhashdist(int i) {
		return this.dhashdist[i];
	}

	public long[] color1() {
		return this.color1;
	}

	public String dhash() {
		return this.dhash;
	}

	public double ahashdist(int i) {
		return this.ahashdist[i];
	}

	public long[] color2() {
		return this.color2;
	}

	public String ahash() {
		return this.ahash;
	}

	public String phash() {
		return this.phash;
	}

	public long[] date() {
		return this.date;
	}

	public double phashdist(int i) {
		return this.phashdist[i];
	}

	public long note() {
		return this.note;
	}

	public long id() {
		return this.id;
	}

	public long[] size() {
		return this.size;
	}
}
