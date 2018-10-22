package com.yunshuju.common.utils;

import java.io.IOException;
import java.lang.reflect.Type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

/**
 * boolean序列化
 */
@JacksonStdImpl
public class DueBooleanStringSerializer extends StdScalarSerializer<Object> {
	private static final long serialVersionUID = 1L;
	public static final DueBooleanStringSerializer instance=new DueBooleanStringSerializer();

	private DueBooleanStringSerializer() { super(String.class, false); }

	@Override
	public boolean isEmpty(SerializerProvider prov, Object value) {
		String str = (String) value;
		return str.length() == 0;
	}

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if(null != value){
			String strValue = value.toString();
			if("false".equals(strValue) || "true".equals(strValue)){
				gen.writeBoolean(Boolean.parseBoolean(strValue));
				return;
			}
		}
		gen.writeString((String) value);
	}

	@Override
	public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider,
										TypeSerializer typeSer) throws IOException
	{
		// no type info, just regular serialization
		gen.writeString((String) value);
	}

	@Override
	public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
		return createSchemaNode("string", true);
	}

	@Override
	public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
		visitStringFormat(visitor, typeHint);
	}
}
