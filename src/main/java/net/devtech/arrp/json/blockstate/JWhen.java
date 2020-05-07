package net.devtech.arrp.json.blockstate;

import com.google.gson.*;
import net.minecraft.util.Pair;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JWhen implements Cloneable {
	private final List<Pair<String, String[]>> OR = new ArrayList<>();

	JWhen() {}

	public JWhen add(String condition, String... states) {
		this.OR.add(new Pair<>(condition, states));
		return this;
	}

	public static class Serializer implements JsonSerializer<JWhen> {
		@Override
		public JsonElement serialize(JWhen src, Type typeOfSrc, JsonSerializationContext context) {
			if (src.OR.size() == 1) {
				JsonObject json = new JsonObject();
				Pair<String, String[]> val = src.OR.get(0);
				json.addProperty(val.getLeft(), String.join("|", Arrays.asList(val.getRight())));
				return json;
			} else {
				JsonArray array = new JsonArray();
				for (Pair<String, String[]> val : src.OR) {
					JsonObject json = new JsonObject();
					json.addProperty(val.getLeft(), String.join("|", Arrays.asList(val.getRight())));
					array.add(json);
				}
				return array;
			}
		}
	}

	@Override
	public JWhen clone() {
		try {
			return (JWhen) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
}
