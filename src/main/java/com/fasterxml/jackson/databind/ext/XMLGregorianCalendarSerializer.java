package com.fasterxml.jackson.databind.ext;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.jdk.JavaUtilCalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class XMLGregorianCalendarSerializer
    extends StdSerializer<XMLGregorianCalendar>
{
    final static XMLGregorianCalendarSerializer instance = new XMLGregorianCalendarSerializer();

    final JsonSerializer<Object> _delegate;
    
    public XMLGregorianCalendarSerializer() {
        this(JavaUtilCalendarSerializer.instance);
    }

    @SuppressWarnings("unchecked")
    protected XMLGregorianCalendarSerializer(JsonSerializer<?> del) {
        super(XMLGregorianCalendar.class);
        _delegate = (JsonSerializer<Object>) del;
    }

    @Override
    public JsonSerializer<?> getDelegatee() {
        return _delegate;
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, XMLGregorianCalendar value) {
        return _delegate.isEmpty(provider, _convert(value));
    }

    @Override
    public void serialize(XMLGregorianCalendar value, JsonGenerator gen, SerializerProvider provider)
        throws JacksonException
    {
        _delegate.serialize(_convert(value), gen, provider);
    }

    @Override
    public void serializeWithType(XMLGregorianCalendar value, JsonGenerator gen, SerializerProvider provider,
            TypeSerializer typeSer) throws JacksonException
    {
        _delegate.serializeWithType(_convert(value), gen, provider, typeSer);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) {
        _delegate.acceptJsonFormatVisitor(visitor, null);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
    {
        JsonSerializer<?> ser = prov.handlePrimaryContextualization(_delegate, property);
        if (ser != _delegate) {
            return new XMLGregorianCalendarSerializer(ser);
        }
        return this;
    }

    protected Calendar _convert(XMLGregorianCalendar input) {
        return (input == null) ? null : input.toGregorianCalendar();
    }
}