/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package forma.schema;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataChunk implements org.apache.thrift.TBase<DataChunk, DataChunk._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DataChunk");

  private static final org.apache.thrift.protocol.TField DATASET_FIELD_DESC = new org.apache.thrift.protocol.TField("dataset", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField LOCATION_PROPERTY_FIELD_DESC = new org.apache.thrift.protocol.TField("locationProperty", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField CHUNK_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("chunkValue", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField TEMPORAL_RES_FIELD_DESC = new org.apache.thrift.protocol.TField("temporalRes", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("date", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField PEDIGREE_FIELD_DESC = new org.apache.thrift.protocol.TField("pedigree", org.apache.thrift.protocol.TType.STRUCT, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DataChunkStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DataChunkTupleSchemeFactory());
  }

  public String dataset; // required
  public LocationProperty locationProperty; // required
  public DataValue chunkValue; // required
  public String temporalRes; // required
  public String date; // optional
  public Pedigree pedigree; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DATASET((short)1, "dataset"),
    LOCATION_PROPERTY((short)2, "locationProperty"),
    CHUNK_VALUE((short)3, "chunkValue"),
    TEMPORAL_RES((short)4, "temporalRes"),
    DATE((short)5, "date"),
    PEDIGREE((short)6, "pedigree");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DATASET
          return DATASET;
        case 2: // LOCATION_PROPERTY
          return LOCATION_PROPERTY;
        case 3: // CHUNK_VALUE
          return CHUNK_VALUE;
        case 4: // TEMPORAL_RES
          return TEMPORAL_RES;
        case 5: // DATE
          return DATE;
        case 6: // PEDIGREE
          return PEDIGREE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private _Fields optionals[] = {_Fields.DATE,_Fields.PEDIGREE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DATASET, new org.apache.thrift.meta_data.FieldMetaData("dataset", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LOCATION_PROPERTY, new org.apache.thrift.meta_data.FieldMetaData("locationProperty", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, LocationProperty.class)));
    tmpMap.put(_Fields.CHUNK_VALUE, new org.apache.thrift.meta_data.FieldMetaData("chunkValue", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DataValue.class)));
    tmpMap.put(_Fields.TEMPORAL_RES, new org.apache.thrift.meta_data.FieldMetaData("temporalRes", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATE, new org.apache.thrift.meta_data.FieldMetaData("date", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PEDIGREE, new org.apache.thrift.meta_data.FieldMetaData("pedigree", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Pedigree.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DataChunk.class, metaDataMap);
  }

  public DataChunk() {
  }

  public DataChunk(
    String dataset,
    LocationProperty locationProperty,
    DataValue chunkValue,
    String temporalRes)
  {
    this();
    this.dataset = dataset;
    this.locationProperty = locationProperty;
    this.chunkValue = chunkValue;
    this.temporalRes = temporalRes;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DataChunk(DataChunk other) {
    if (other.isSetDataset()) {
      this.dataset = other.dataset;
    }
    if (other.isSetLocationProperty()) {
      this.locationProperty = new LocationProperty(other.locationProperty);
    }
    if (other.isSetChunkValue()) {
      this.chunkValue = new DataValue(other.chunkValue);
    }
    if (other.isSetTemporalRes()) {
      this.temporalRes = other.temporalRes;
    }
    if (other.isSetDate()) {
      this.date = other.date;
    }
    if (other.isSetPedigree()) {
      this.pedigree = new Pedigree(other.pedigree);
    }
  }

  public DataChunk deepCopy() {
    return new DataChunk(this);
  }

  @Override
  public void clear() {
    this.dataset = null;
    this.locationProperty = null;
    this.chunkValue = null;
    this.temporalRes = null;
    this.date = null;
    this.pedigree = null;
  }

  public String getDataset() {
    return this.dataset;
  }

  public DataChunk setDataset(String dataset) {
    this.dataset = dataset;
    return this;
  }

  public void unsetDataset() {
    this.dataset = null;
  }

  /** Returns true if field dataset is set (has been assigned a value) and false otherwise */
  public boolean isSetDataset() {
    return this.dataset != null;
  }

  public void setDatasetIsSet(boolean value) {
    if (!value) {
      this.dataset = null;
    }
  }

  public LocationProperty getLocationProperty() {
    return this.locationProperty;
  }

  public DataChunk setLocationProperty(LocationProperty locationProperty) {
    this.locationProperty = locationProperty;
    return this;
  }

  public void unsetLocationProperty() {
    this.locationProperty = null;
  }

  /** Returns true if field locationProperty is set (has been assigned a value) and false otherwise */
  public boolean isSetLocationProperty() {
    return this.locationProperty != null;
  }

  public void setLocationPropertyIsSet(boolean value) {
    if (!value) {
      this.locationProperty = null;
    }
  }

  public DataValue getChunkValue() {
    return this.chunkValue;
  }

  public DataChunk setChunkValue(DataValue chunkValue) {
    this.chunkValue = chunkValue;
    return this;
  }

  public void unsetChunkValue() {
    this.chunkValue = null;
  }

  /** Returns true if field chunkValue is set (has been assigned a value) and false otherwise */
  public boolean isSetChunkValue() {
    return this.chunkValue != null;
  }

  public void setChunkValueIsSet(boolean value) {
    if (!value) {
      this.chunkValue = null;
    }
  }

  public String getTemporalRes() {
    return this.temporalRes;
  }

  public DataChunk setTemporalRes(String temporalRes) {
    this.temporalRes = temporalRes;
    return this;
  }

  public void unsetTemporalRes() {
    this.temporalRes = null;
  }

  /** Returns true if field temporalRes is set (has been assigned a value) and false otherwise */
  public boolean isSetTemporalRes() {
    return this.temporalRes != null;
  }

  public void setTemporalResIsSet(boolean value) {
    if (!value) {
      this.temporalRes = null;
    }
  }

  public String getDate() {
    return this.date;
  }

  public DataChunk setDate(String date) {
    this.date = date;
    return this;
  }

  public void unsetDate() {
    this.date = null;
  }

  /** Returns true if field date is set (has been assigned a value) and false otherwise */
  public boolean isSetDate() {
    return this.date != null;
  }

  public void setDateIsSet(boolean value) {
    if (!value) {
      this.date = null;
    }
  }

  public Pedigree getPedigree() {
    return this.pedigree;
  }

  public DataChunk setPedigree(Pedigree pedigree) {
    this.pedigree = pedigree;
    return this;
  }

  public void unsetPedigree() {
    this.pedigree = null;
  }

  /** Returns true if field pedigree is set (has been assigned a value) and false otherwise */
  public boolean isSetPedigree() {
    return this.pedigree != null;
  }

  public void setPedigreeIsSet(boolean value) {
    if (!value) {
      this.pedigree = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DATASET:
      if (value == null) {
        unsetDataset();
      } else {
        setDataset((String)value);
      }
      break;

    case LOCATION_PROPERTY:
      if (value == null) {
        unsetLocationProperty();
      } else {
        setLocationProperty((LocationProperty)value);
      }
      break;

    case CHUNK_VALUE:
      if (value == null) {
        unsetChunkValue();
      } else {
        setChunkValue((DataValue)value);
      }
      break;

    case TEMPORAL_RES:
      if (value == null) {
        unsetTemporalRes();
      } else {
        setTemporalRes((String)value);
      }
      break;

    case DATE:
      if (value == null) {
        unsetDate();
      } else {
        setDate((String)value);
      }
      break;

    case PEDIGREE:
      if (value == null) {
        unsetPedigree();
      } else {
        setPedigree((Pedigree)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DATASET:
      return getDataset();

    case LOCATION_PROPERTY:
      return getLocationProperty();

    case CHUNK_VALUE:
      return getChunkValue();

    case TEMPORAL_RES:
      return getTemporalRes();

    case DATE:
      return getDate();

    case PEDIGREE:
      return getPedigree();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DATASET:
      return isSetDataset();
    case LOCATION_PROPERTY:
      return isSetLocationProperty();
    case CHUNK_VALUE:
      return isSetChunkValue();
    case TEMPORAL_RES:
      return isSetTemporalRes();
    case DATE:
      return isSetDate();
    case PEDIGREE:
      return isSetPedigree();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DataChunk)
      return this.equals((DataChunk)that);
    return false;
  }

  public boolean equals(DataChunk that) {
    if (that == null)
      return false;

    boolean this_present_dataset = true && this.isSetDataset();
    boolean that_present_dataset = true && that.isSetDataset();
    if (this_present_dataset || that_present_dataset) {
      if (!(this_present_dataset && that_present_dataset))
        return false;
      if (!this.dataset.equals(that.dataset))
        return false;
    }

    boolean this_present_locationProperty = true && this.isSetLocationProperty();
    boolean that_present_locationProperty = true && that.isSetLocationProperty();
    if (this_present_locationProperty || that_present_locationProperty) {
      if (!(this_present_locationProperty && that_present_locationProperty))
        return false;
      if (!this.locationProperty.equals(that.locationProperty))
        return false;
    }

    boolean this_present_chunkValue = true && this.isSetChunkValue();
    boolean that_present_chunkValue = true && that.isSetChunkValue();
    if (this_present_chunkValue || that_present_chunkValue) {
      if (!(this_present_chunkValue && that_present_chunkValue))
        return false;
      if (!this.chunkValue.equals(that.chunkValue))
        return false;
    }

    boolean this_present_temporalRes = true && this.isSetTemporalRes();
    boolean that_present_temporalRes = true && that.isSetTemporalRes();
    if (this_present_temporalRes || that_present_temporalRes) {
      if (!(this_present_temporalRes && that_present_temporalRes))
        return false;
      if (!this.temporalRes.equals(that.temporalRes))
        return false;
    }

    boolean this_present_date = true && this.isSetDate();
    boolean that_present_date = true && that.isSetDate();
    if (this_present_date || that_present_date) {
      if (!(this_present_date && that_present_date))
        return false;
      if (!this.date.equals(that.date))
        return false;
    }

    boolean this_present_pedigree = true && this.isSetPedigree();
    boolean that_present_pedigree = true && that.isSetPedigree();
    if (this_present_pedigree || that_present_pedigree) {
      if (!(this_present_pedigree && that_present_pedigree))
        return false;
      if (!this.pedigree.equals(that.pedigree))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_dataset = true && (isSetDataset());
    builder.append(present_dataset);
    if (present_dataset)
      builder.append(dataset);

    boolean present_locationProperty = true && (isSetLocationProperty());
    builder.append(present_locationProperty);
    if (present_locationProperty)
      builder.append(locationProperty);

    boolean present_chunkValue = true && (isSetChunkValue());
    builder.append(present_chunkValue);
    if (present_chunkValue)
      builder.append(chunkValue);

    boolean present_temporalRes = true && (isSetTemporalRes());
    builder.append(present_temporalRes);
    if (present_temporalRes)
      builder.append(temporalRes);

    boolean present_date = true && (isSetDate());
    builder.append(present_date);
    if (present_date)
      builder.append(date);

    boolean present_pedigree = true && (isSetPedigree());
    builder.append(present_pedigree);
    if (present_pedigree)
      builder.append(pedigree);

    return builder.toHashCode();
  }

  public int compareTo(DataChunk other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    DataChunk typedOther = (DataChunk)other;

    lastComparison = Boolean.valueOf(isSetDataset()).compareTo(typedOther.isSetDataset());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataset()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataset, typedOther.dataset);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLocationProperty()).compareTo(typedOther.isSetLocationProperty());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocationProperty()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.locationProperty, typedOther.locationProperty);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetChunkValue()).compareTo(typedOther.isSetChunkValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChunkValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.chunkValue, typedOther.chunkValue);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTemporalRes()).compareTo(typedOther.isSetTemporalRes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTemporalRes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.temporalRes, typedOther.temporalRes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDate()).compareTo(typedOther.isSetDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.date, typedOther.date);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPedigree()).compareTo(typedOther.isSetPedigree());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPedigree()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pedigree, typedOther.pedigree);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DataChunk(");
    boolean first = true;

    sb.append("dataset:");
    if (this.dataset == null) {
      sb.append("null");
    } else {
      sb.append(this.dataset);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("locationProperty:");
    if (this.locationProperty == null) {
      sb.append("null");
    } else {
      sb.append(this.locationProperty);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("chunkValue:");
    if (this.chunkValue == null) {
      sb.append("null");
    } else {
      sb.append(this.chunkValue);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("temporalRes:");
    if (this.temporalRes == null) {
      sb.append("null");
    } else {
      sb.append(this.temporalRes);
    }
    first = false;
    if (isSetDate()) {
      if (!first) sb.append(", ");
      sb.append("date:");
      if (this.date == null) {
        sb.append("null");
      } else {
        sb.append(this.date);
      }
      first = false;
    }
    if (isSetPedigree()) {
      if (!first) sb.append(", ");
      sb.append("pedigree:");
      if (this.pedigree == null) {
        sb.append("null");
      } else {
        sb.append(this.pedigree);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DataChunkStandardSchemeFactory implements SchemeFactory {
    public DataChunkStandardScheme getScheme() {
      return new DataChunkStandardScheme();
    }
  }

  private static class DataChunkStandardScheme extends StandardScheme<DataChunk> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DataChunk struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DATASET
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dataset = iprot.readString();
              struct.setDatasetIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LOCATION_PROPERTY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.locationProperty = new LocationProperty();
              struct.locationProperty.read(iprot);
              struct.setLocationPropertyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CHUNK_VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.chunkValue = new DataValue();
              struct.chunkValue.read(iprot);
              struct.setChunkValueIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TEMPORAL_RES
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.temporalRes = iprot.readString();
              struct.setTemporalResIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.date = iprot.readString();
              struct.setDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // PEDIGREE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.pedigree = new Pedigree();
              struct.pedigree.read(iprot);
              struct.setPedigreeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DataChunk struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dataset != null) {
        oprot.writeFieldBegin(DATASET_FIELD_DESC);
        oprot.writeString(struct.dataset);
        oprot.writeFieldEnd();
      }
      if (struct.locationProperty != null) {
        oprot.writeFieldBegin(LOCATION_PROPERTY_FIELD_DESC);
        struct.locationProperty.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.chunkValue != null) {
        oprot.writeFieldBegin(CHUNK_VALUE_FIELD_DESC);
        struct.chunkValue.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.temporalRes != null) {
        oprot.writeFieldBegin(TEMPORAL_RES_FIELD_DESC);
        oprot.writeString(struct.temporalRes);
        oprot.writeFieldEnd();
      }
      if (struct.date != null) {
        if (struct.isSetDate()) {
          oprot.writeFieldBegin(DATE_FIELD_DESC);
          oprot.writeString(struct.date);
          oprot.writeFieldEnd();
        }
      }
      if (struct.pedigree != null) {
        if (struct.isSetPedigree()) {
          oprot.writeFieldBegin(PEDIGREE_FIELD_DESC);
          struct.pedigree.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DataChunkTupleSchemeFactory implements SchemeFactory {
    public DataChunkTupleScheme getScheme() {
      return new DataChunkTupleScheme();
    }
  }

  private static class DataChunkTupleScheme extends TupleScheme<DataChunk> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DataChunk struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetDataset()) {
        optionals.set(0);
      }
      if (struct.isSetLocationProperty()) {
        optionals.set(1);
      }
      if (struct.isSetChunkValue()) {
        optionals.set(2);
      }
      if (struct.isSetTemporalRes()) {
        optionals.set(3);
      }
      if (struct.isSetDate()) {
        optionals.set(4);
      }
      if (struct.isSetPedigree()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetDataset()) {
        oprot.writeString(struct.dataset);
      }
      if (struct.isSetLocationProperty()) {
        struct.locationProperty.write(oprot);
      }
      if (struct.isSetChunkValue()) {
        struct.chunkValue.write(oprot);
      }
      if (struct.isSetTemporalRes()) {
        oprot.writeString(struct.temporalRes);
      }
      if (struct.isSetDate()) {
        oprot.writeString(struct.date);
      }
      if (struct.isSetPedigree()) {
        struct.pedigree.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DataChunk struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.dataset = iprot.readString();
        struct.setDatasetIsSet(true);
      }
      if (incoming.get(1)) {
        struct.locationProperty = new LocationProperty();
        struct.locationProperty.read(iprot);
        struct.setLocationPropertyIsSet(true);
      }
      if (incoming.get(2)) {
        struct.chunkValue = new DataValue();
        struct.chunkValue.read(iprot);
        struct.setChunkValueIsSet(true);
      }
      if (incoming.get(3)) {
        struct.temporalRes = iprot.readString();
        struct.setTemporalResIsSet(true);
      }
      if (incoming.get(4)) {
        struct.date = iprot.readString();
        struct.setDateIsSet(true);
      }
      if (incoming.get(5)) {
        struct.pedigree = new Pedigree();
        struct.pedigree.read(iprot);
        struct.setPedigreeIsSet(true);
      }
    }
  }

}

