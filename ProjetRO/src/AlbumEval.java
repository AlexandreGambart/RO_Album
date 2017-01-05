import java.io.IOException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AlbumEval extends Eval {

	@SuppressWarnings("unused")
	private long page;
	@SuppressWarnings("unused")
	private long pagesize;
	@SuppressWarnings("unused")
	private String basename;
	@SuppressWarnings("unused")
	private long pagewidth;
	@SuppressWarnings("unused")
	private long pageheight;

	private Picture[] pictures;

	private int[][] commonTags;

	public AlbumEval(int n) {
		super(n);

		String albumFileName = "./data/json/info-album.json";
		JSONParser parser = new JSONParser();

		try {
			FileReader file = new FileReader(albumFileName);
			Object obj = parser.parse(file);
			file.close();
			JSONObject jsonObject = (JSONObject) obj;

			this.page = (long) jsonObject.get("page");

			JSONArray pagesizeArr = (JSONArray) jsonObject.get("pagesize");
			this.pagesize = (Long) pagesizeArr.get(1); // 6

			this.pictures = new Picture[55];
			for (int i = 0; i < 55; i++) {
				this.pictures[i] = new Picture(i);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.commonTags = new int[55][55];
		for (int pict1 = 0; pict1 < 55; pict1++) {
			for (int pict2 = 0; pict2 < 55; pict2++) {
				this.commonTags[pict1][pict2] = getCommonTags(
						this.pictures[pict1].tags(),
						this.pictures[pict2].tags());
			}
		}

	}

	// Fonction qui retourne le nombre de tags en commun entre deux photos.
	private int getCommonTags(JSONObject tagsPictOne, JSONObject tagsPictTwo) {
		JSONArray classesPictOneArray = (JSONArray) tagsPictOne.get("classes");
		JSONArray classesPictTwoArray = (JSONArray) tagsPictTwo.get("classes");

		int commonTags = 0;
		for (int i = 0; i < classesPictOneArray.size(); i++) {
			for (int j = 0; j < classesPictTwoArray.size(); j++) {
				if (classesPictOneArray.get(i).equals(
						classesPictTwoArray.get(j))) {
					commonTags++;
				}
			}
		}

		return commonTags;
	}

	public Picture picture(int n) {
		return this.pictures[n];
	}

	
	 private int evalByTags(Solution solution) { int value = 0;
	 
	 for (int i = 0; i < 9; i++) { value +=
	 (this.commonTags[solution.solution[i]][solution.solution[i + 1]] +
	 this.commonTags[solution.solution[i]][solution.solution[i + 2]] +
	 this.commonTags[solution.solution[i]][solution.solution[i + 3]] +
	 this.commonTags[solution.solution[i]][solution.solution[i + 4]] +
	 this.commonTags[solution.solution[i]][solution.solution[i + 5]]); }
	 
	 return value; }
	
	 
	/*
	 * private int evalTags(int photo1, int photo2) { JSONObject tagsPictOne =
	 * this.pictures[photo1].tags(); JSONObject tagsPictTwo =
	 * this.pictures[photo2].tags();
	 * 
	 * JSONArray photo1Tags = (JSONArray) tagsPictOne.get("classes"); JSONArray
	 * photo2Tags = (JSONArray) tagsPictTwo.get("classes"); int differentTags =
	 * 0;
	 * 
	 * for (Object tag : photo1Tags) { if (!photo2Tags.contains((String) tag)) {
	 * differentTags += 1; } }
	 * 
	 * 
	 * 
	 * return ((differentTags * 100) / 20); }
	 * 
	 * private int evalPage(int page) { int tagScore = 0;
	 * 
	 * for (int photo = page; photo < page + 6; ++photo) { if (photo % 2 == 0) {
	 * // colorScore += evalColor(photo, photo + 1); tagScore += evalTags(photo,
	 * photo + 1); } }
	 * 
	 * // colorScore /= 27; tagScore /= 27;
	 * 
	 * return (tagScore); // 20% de la couleur + 80% par rapport aux tags }
	 */
	@Override
	public void apply(Solution solution) {
		int value = 0;

		for (int i = 0; i < 9; i++) {

			value += (this.commonTags[solution.solution[i]][solution.solution[i + 1]] + this.commonTags[solution.solution[i]][solution.solution[i + 2]]) * 3;

			value += (this.commonTags[solution.solution[i]][solution.solution[i + 3]] + this.commonTags[solution.solution[i]][solution.solution[i + 4]]) * 2;

			value += this.commonTags[solution.solution[i]][solution.solution[i + 5]];
			
		}
		
//		System.out.println("---");

		solution.fitness((double) value);
	}

	/*
	 * @Override public void evalbyAhash(Solution solution) { int value= 0;
	 * 
	 * for(int i = 0; i < solution.size()/6; i ++){ value +=
	 * (_ahashArray[solution._solution[i]][solution._solution[i+1]] +
	 * _ahashArray[solution._solution[i]][solution._solution[i+2]]) * 3; value
	 * += (_ahashArray[solution._solution[i]][solution._solution[i+3]] +
	 * _ahashArray[solution._solution[i]][solution._solution[i+4]]) * 2; value
	 * += _ahashArray[solution._solution[i]][solution._solution[i+5]]; }
	 * 
	 * //System.out.println(value); solution.fitness((double)value);
	 * 
	 * }
	 */
}
