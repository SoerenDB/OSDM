package jsonImportExport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.Base64;

import Gtm.AfterSalesCondition;
import Gtm.AfterSalesRule;
import Gtm.AfterSalesRules;
import Gtm.AllowedPersonalDataChanges;
import Gtm.AlternativeRoute;
import Gtm.BarcodeTypes;
import Gtm.Calendar;
import Gtm.Calendars;
import Gtm.Carrier;
import Gtm.CarrierConstraint;
import Gtm.CarrierConstraints;
import Gtm.CarrierResourceLocation;
import Gtm.CarrierResourceLocations;
import Gtm.Clusters;
import Gtm.CombinationConstraints;
import Gtm.ConnectionPoint;
import Gtm.ConnectionPoints;
import Gtm.ControlDataExchangeTypes;
import Gtm.CurrencyPrice;
import Gtm.Edge;
import Gtm.FareCombinationModel;
import Gtm.FareElement;
import Gtm.FareElements;
import Gtm.FareResourceLocations;
import Gtm.FulfillmentConstraint;
import Gtm.FulfillmentConstraints;
import Gtm.FulfillmentType;
import Gtm.GeneralTariffModel;
import Gtm.Line;
import Gtm.OnlineResource;
import Gtm.OnlineServiceType;
import Gtm.PassengerCombinationConstraint;
import Gtm.PassengerConstraint;
import Gtm.PassengerConstraints;
import Gtm.PersonalDataConstraint;
import Gtm.PersonalDataConstraints;
import Gtm.PersonalDataTransferType;
import Gtm.Polygone;
import Gtm.Price;
import Gtm.Prices;
import Gtm.ReductionCard;
import Gtm.ReductionCards;
import Gtm.ReductionConstraint;
import Gtm.ReductionConstraints;
import Gtm.RegionalConstraint;
import Gtm.RegionalConstraints;
import Gtm.RegionalValidity;
import Gtm.RelativeTime;
import Gtm.RequiredPersonalData;
import Gtm.RequiredReductionCard;
import Gtm.ReservationParameter;
import Gtm.ReservationParameters;
import Gtm.ReservationPreferenceGroup;
import Gtm.ReturnValidityConstraint;
import Gtm.Route;
import Gtm.SalesAvailabilityConstraint;
import Gtm.SalesAvailabilityConstraints;
import Gtm.SalesRestriction;
import Gtm.ServiceBrand;
import Gtm.ServiceClass;
import Gtm.ServiceClassDefinitions;
import Gtm.ServiceConstraint;
import Gtm.ServiceConstraints;
import Gtm.ServiceLevel;
import Gtm.ServiceLevelDefinitions;
import Gtm.Station;
import Gtm.StationResourceLocation;
import Gtm.StationResourceLocations;
import Gtm.StationSet;
import Gtm.Text;
import Gtm.Texts;
import Gtm.TimeRange;
import Gtm.TrainResourceLocation;
import Gtm.TrainResourceLocations;
import Gtm.Translation;
import Gtm.TravelValidityConstraint;
import Gtm.TravelValidityConstraints;
import Gtm.VATDetail;
import Gtm.ViaStation;
import Gtm.WeekDay;
import Gtm.Zone;
import Gtm.ZoneDefinition;
import Gtm.ZoneDefinitions;
import gtm.AfterSalesConditionDef;
import gtm.AfterSalesRulesDef;
import gtm.AllowedChange;
import gtm.CalendarDef;
import gtm.CarrierConstraintDef;
import gtm.CarrierResourceLocationDef;
import gtm.CombinationConstraint;
import gtm.ConnectionPointDef;
import gtm.CrossBorderCondition;
import gtm.CurrencyPriceDef;
import gtm.Delivery;
import gtm.ExcludedTimeRange;
import gtm.FareCombinationConstraintDef;
import gtm.FareCombinationModelDef;
import gtm.FareDelivery;
import gtm.FareElementDef;
import gtm.FareResourceLocationDef;
import gtm.FareStructure;
import gtm.FareStructureDeliveryDef;
import gtm.FullfillmentConstraintDef;
import gtm.GeoCoordinateDef;
import gtm.IncludedFreePassenger;
import gtm.LegacyAccountingIdentifier;
import gtm.LineDef;
import gtm.OnlineResourceDef;
import gtm.PassengerConstraintDef;
import gtm.PersonalDataConstraintDef;
import gtm.PolygoneDef;
import gtm.PriceDef;
import gtm.ReductionCardDef;
import gtm.ReductionConstraintDef;
import gtm.RegionalConstraintDef;
import gtm.RegionalValidityDef;
import gtm.RelativeTimeDef;
import gtm.RequiredCard;
import gtm.RequiredDatum;
import gtm.ReservationOptionGroupDef;
import gtm.ReservationOptions;
import gtm.ReservationParameterDef;
import gtm.ReservationParams9181;
import gtm.ReturnConstraint;
import gtm.SalesAvailabilityConstraintDef;
import gtm.ServiceClassDef;
import gtm.ServiceConstraintDef;
import gtm.ServiceLevelDef;
import gtm.StationDef;
import gtm.StationResourceLocationDef;
import gtm.TextDef;
import gtm.TrainResourceLocationDef;
import gtm.TranslationDef;
import gtm.TravelValidityConstraintDef;
import gtm.VatDetail;
import gtm.ViaStationsDef;
import gtm.ZoneDef;
import gtm.ZoneDefinitionDef;

public class GtmJsonUtils {
	
	
	private static DateFormat jsondf = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");

	
	public static FareDelivery convertToJson(GeneralTariffModel gtm) {
		
		if (gtm == null || gtm.getDelivery() == null || gtm.getFareStructure() == null) return null;
		
	
		FareDelivery export = new FareDelivery();
		FareStructureDeliveryDef exportData = new FareStructureDeliveryDef();
		export.setFareStructureDelivery(exportData);
		
		exportData.setDelivery(convertDeliveryToJson(gtm.getDelivery()));
		
		FareStructure fares = new FareStructure();
		exportData.setFareStructure(fares);

					
		if (gtm.getFareStructure().getAfterSalesRules() != null) {
			fares.setAfterSalesRules(convertAfterSalesConditionsToJson(gtm.getFareStructure().getAfterSalesRules()));
		}
		
		if (gtm.getFareStructure().getCalendars() != null) {
			fares.setCalendars(convertCalendars(gtm.getFareStructure().getCalendars()));
		}
		
		if (gtm.getFareStructure().getCarrierConstraints() != null) {
			fares.setCarrierConstraints(convertCarrierConstraints(gtm.getFareStructure().getCarrierConstraints()));
		}
		
		if (gtm.getFareStructure().getCombinationConstraints() != null) {
			fares.setCombinationConstraints(convertCombinationConstraints(gtm.getFareStructure().getCombinationConstraints()));
		}
		
		if (gtm.getFareStructure().getConnectionPoints() != null) {
			fares.setConnectionPoints(convertConnectionPoints(gtm.getFareStructure().getConnectionPoints()));
		}
				
		if (gtm.getFareStructure().getFareElements() != null) {
			fares.setFareElements(convertFareElements(gtm.getFareStructure().getFareElements()));
		}
		
		if (gtm.getFareStructure().getFareResourceLocations() != null) {
			fares.setFareResourceLocation(convertFareResourceLocation(gtm.getFareStructure().getFareResourceLocations()));
		}
		
		if (gtm.getFareStructure().getFulfillmentConstraints() != null) {
			fares.setFullfillmentConstraints(convertFullfillmentConstraints(gtm.getFareStructure().getFulfillmentConstraints()));
		}
		
		if (gtm.getFareStructure().getPassengerConstraints() != null) {
			fares.setPassengerConstraints(convertPassengerConstraints(gtm.getFareStructure().getPassengerConstraints()));
		}
		
		if (gtm.getFareStructure().getPersonalDataConstraints() != null) {
			fares.setPersonalDataConstraints(convertPersonalDataConstraints(gtm.getFareStructure().getPersonalDataConstraints()));
		}
		
		if (gtm.getFareStructure().getPrices() != null) {
			fares.setPrices(convertPrices(gtm.getFareStructure().getPrices()));
		}
		
		if (gtm.getFareStructure().getReductionCards() != null) {
			fares.setReductionCards(convertReductionCards(gtm.getFareStructure().getReductionCards()));
		}
		
		if (gtm.getFareStructure().getReductionConstraints() != null) {
			fares.setReductionConstraints(convertReductionConstraints(gtm.getFareStructure().getReductionConstraints()));
		}
		
		if (gtm.getFareStructure().getRegionalConstraints() != null) {
			fares.setRegionalConstraints(convertRegionalConstraints(gtm.getFareStructure().getRegionalConstraints()));
		}
		
		if (gtm.getFareStructure().getReservationParameters() != null) {
			fares.setReservationParameters(convertReservationParameters(gtm.getFareStructure().getReservationParameters()));
		}
		
		if (gtm.getFareStructure().getSalesAvailabilityConstraints() != null) {
			fares.setSalesAvailabilityConstraint(convertSalesAvailabilityConstraints(gtm.getFareStructure().getSalesAvailabilityConstraints()));
		}
		
		if (gtm.getFareStructure().getServiceClassDefinitions() != null) {
			fares.setServiceClassDefinitions(convertServiceClassDefinitions(gtm.getFareStructure().getServiceClassDefinitions()));
		}
		
		if (gtm.getFareStructure().getServiceConstraints() != null) {
			fares.setServiceConstraints(convertServiceConstraints(gtm.getFareStructure().getServiceConstraints()));
		}
		
		if (gtm.getFareStructure().getServiceLevelDefinitions() != null) {
			fares.setServiceLevelDefinitions(convertServiceLevelDefinitions(gtm.getFareStructure().getServiceLevelDefinitions()));
		}
		
		if (gtm.getFareStructure().getSupportedOnlineServices() != null && !gtm.getFareStructure().getSupportedOnlineServices().getSupportedOnlineServices().isEmpty()) {
			ArrayList<String> services = new ArrayList<String>();
			for (OnlineServiceType serviceType : gtm.getFareStructure().getSupportedOnlineServices().getSupportedOnlineServices()) {
				services.add(serviceType.getName());
			}
			fares.setSupportedOnlineServices(services);
		}
		
		if (gtm.getFareStructure().getTexts() != null) {
			fares.setTexts(convertTexts(gtm.getFareStructure().getTexts()));
		}
			
		if (gtm.getFareStructure().getTravelValidityConstraints() != null) {
			fares.setTravelValidityConstraints(convertTravelValidityConstraints(gtm.getFareStructure().getTravelValidityConstraints()));
		}
		
		if (gtm.getFareStructure().getZoneDefinitions() != null) {
			fares.setZoneDefinitions(convertZoneDefinitions(gtm.getFareStructure().getZoneDefinitions()));
		}
		
		return null;
	}




	private static List<ZoneDefinitionDef> convertZoneDefinitions(ZoneDefinitions list) {
		if (list.getZoneDefinitions().isEmpty()) return null;
		ArrayList<ZoneDefinitionDef> listJson = new ArrayList<ZoneDefinitionDef>();
		for (ZoneDefinition element: list.getZoneDefinitions()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}


	private static ZoneDefinitionDef convertToJson(ZoneDefinition zone) {
		ZoneDefinitionDef zoneJ = new ZoneDefinitionDef();
		
		zoneJ.setId(zone.getId());
		zoneJ.setName(zone.getName());
		zoneJ.setZoneId(Integer.toString(zone.getZoneId()));
		
		if (zone.getStations()!= null && !zone.getStations().isEmpty()) {
			
			Set<StationDef> list = new HashSet<StationDef>();
		
			for (Station station: zone.getStations()) {
				
				StationDef stationJ = new StationDef();
				stationJ.setCountry(Integer.toString(station.getCountry().getCode()));
				stationJ.setLocalCode(station.getCode());
				list.add(stationJ);
			}
			zoneJ.setStations(list);
		}
		return zoneJ;
	}




	private static List<TravelValidityConstraintDef> convertTravelValidityConstraints(
			TravelValidityConstraints list) {
		if (list.getTravelValidityConstraints().isEmpty()) return null;
		ArrayList<TravelValidityConstraintDef> listJson = new ArrayList<TravelValidityConstraintDef>();
		for (TravelValidityConstraint element: list.getTravelValidityConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static TravelValidityConstraintDef convertToJson(TravelValidityConstraint tv) {
		
		TravelValidityConstraintDef tvJ = new TravelValidityConstraintDef();
		
		tvJ.setId(tv.getId());
				
		tvJ.setNumberOfTravelDays(tv.getTravelDays()); 
		
		if (tv.getReturnConstraint() != null) {
			tvJ.setReturnConstraint(convertToJson(tv.getReturnConstraint()));
		}
		
		if (tv.getValidDays() != null) {
			tvJ.setValidTravelDates(convertToJson(tv.getValidDays()));
		}
		
		if (tv.getExcludedTimeRange()!= null && !tv.getExcludedTimeRange().isEmpty()) {
			
			ArrayList<ExcludedTimeRange> listJ = new ArrayList<ExcludedTimeRange>();
			
			for (Gtm.ExcludedTimeRange tr : tv.getExcludedTimeRange()) {				
				listJ.add(convertToJson(tr));
			}
			tvJ.setExcludedTimeRange(listJ);
		}
		return tvJ;
	}




	private static ExcludedTimeRange convertToJson(TimeRange tr) {
		
		ExcludedTimeRange trJ = new ExcludedTimeRange();
		trJ.setFrom(tr.getFrom());
		trJ.setUntil(tr.getUntil());
		trJ.setScope(tr.getScope().getName());

		return trJ;
	}


	private static ReturnConstraint convertToJson(ReturnValidityConstraint rc) {
		
		ReturnConstraint rcJ = new ReturnConstraint();
		rcJ.setEarliestReturn(rc.getEarliestReturn());
		rcJ.setLatestReturn(rc.getLatestReturn());
		
		if (rc.getExcludedWeekdays() != null && !rc.getExcludedWeekdays().isEmpty() ) {
			
			ArrayList<Integer> listJ = new ArrayList<Integer>();
			
			for (WeekDay day : rc.getExcludedWeekdays()) {
				listJ.add(new Integer(day.getValue()));
			}		
			rcJ.setExcludedWeekdays(listJ);
		}
		return rcJ;
	}




	private static List<TextDef> convertTexts(Texts list) {
		if (list.getTexts().isEmpty()) return null;
		ArrayList<TextDef> listJson = new ArrayList<TextDef>();
		for (Text element: list.getTexts()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static TextDef convertToJson(Text text) {
		
		TextDef textJ = new TextDef();
		
		textJ.setId(text.getId());
		textJ.setShortText(text.getShortTextICAO());
		textJ.setShortTextUtf8(text.getShortTextUTF8());
		textJ.setText(text.getTextICAO());
		textJ.setTextUTF8(text.getTextUTF8());
		
		if (text.getTranslations() != null && !text.getTranslations().isEmpty()) {
			
			ArrayList<TranslationDef> listJ = new ArrayList<TranslationDef>();
			
			for (Translation trans: text.getTranslations()) {
				
				TranslationDef transJ = new TranslationDef();
				
				if (trans.getLanguage()!=null) {
					transJ.setLanguage(trans.getLanguage().getName());
				}
				transJ.setShortText(trans.getShortTextICAO());
				transJ.setShortTextUtf8(trans.getShortTextUTF8());
				transJ.setText(trans.getTextICAO());
				transJ.setTextUTF8(trans.getTextUTF8());				
								
				listJ.add(transJ);
			}
			
			
			
			textJ.setTranslations(listJ);
		}

		return textJ;
	}




	private static List<ServiceLevelDef> convertServiceLevelDefinitions(
			ServiceLevelDefinitions list) {
		if (list.getServiceLevelDefinition().isEmpty()) return null;
		ArrayList<ServiceLevelDef> listJson = new ArrayList<ServiceLevelDef>();
		for (ServiceLevel element: list.getServiceLevelDefinition()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static ServiceLevelDef convertToJson(ServiceLevel sl) {
		
		 ServiceLevelDef slJ = new  ServiceLevelDef();
		 
		 slJ.setId(sl.getId());
		 if (sl.getText()!= null) {
			 slJ.setTextRef(sl.getText().getId());
		 }
		 if (sl.getReservationParameter()!= null) {
			 slJ.setReservationParameterId(sl.getReservationParameter().getId());
		 }
		 slJ.setIncludesClassName(sl.isIncludesClassName());
		
		return slJ;
	}




	private static List<ServiceConstraintDef> convertServiceConstraints(ServiceConstraints list) {
		if (list.getServiceConstraints().isEmpty()) return null;
		ArrayList<ServiceConstraintDef> listJson = new ArrayList<ServiceConstraintDef>();
		for (ServiceConstraint element: list.getServiceConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static ServiceConstraintDef convertToJson(ServiceConstraint sc) {
		
		ServiceConstraintDef scJ = new ServiceConstraintDef();
		
		scJ.setId(sc.getId());
		
		scJ.setExcludedServiceBrands(convertServiceBrandsToJson(sc.getExcludedServiceBrands()));

		scJ.setIncludedServiceBrands(convertServiceBrandsToJson(sc.getIncludedServiceBrands()));		
		
		return scJ;

	}



	private static List<Integer> convertServiceBrandsToJson(EList<ServiceBrand> sbl) {
		if (sbl == null || sbl.isEmpty()) return null;
			
		ArrayList<Integer> listJ = new ArrayList<Integer>();
		for (ServiceBrand sb : sbl) {
				listJ.add(new Integer(sb.getCode()));				
		}
			
		return listJ;
	}

	private static List<String> convertCarriersToJson(EList<Carrier> cl) {
		if (cl == null || cl.isEmpty()) return null;
			
		ArrayList<String> listJ = new ArrayList<String>();
		for (Carrier c : cl) {
				listJ.add(c.getCode());				
		}
			
		return listJ;
	}


	private static List<ServiceClassDef> convertServiceClassDefinitions(ServiceClassDefinitions list) {
		if (list.getServiceClassDefinitions().isEmpty()) return null;
		ArrayList<ServiceClassDef> listJson = new ArrayList<ServiceClassDef>();
		for (ServiceClass element: list.getServiceClassDefinitions()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static ServiceClassDef convertToJson(ServiceClass sc) {
		
		ServiceClassDef scJ = new ServiceClassDef();
		
		if (sc.getId()!= null) {
			scJ.setId(sc.getId().getName());
		}
		if (sc.getText()!=null) {
			scJ.setTextId(sc.getText().getId());
		}
		if (sc.getClassicClass()!= null) {
			scJ.setClassicClass(sc.getClassicClass().getName());
		}
		return scJ;
	}




	private static List<ReservationParameterDef> convertReservationParameters(ReservationParameters list) {
		if (list.getReservationParameters().isEmpty()) return null;
		ArrayList<ReservationParameterDef> listJson = new ArrayList<ReservationParameterDef>();
		for (ReservationParameter element: list.getReservationParameters()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static ReservationParameterDef convertToJson(ReservationParameter r) {
		
		ReservationParameterDef rJ = new ReservationParameterDef();
		
		rJ.setId(r.getId());
		rJ.setReservationRequired(!r.isOptionalReservation());
		rJ.setReservationParams9181(convertToJson(r.getParams9181()));
		rJ.setReservationOptions(convertToJson(r.getOptions()));

		return rJ;
	}




	private static ReservationOptions convertToJson(Gtm.ReservationOptions o) {
		if (o == null) return null;
		ReservationOptions oJ = new ReservationOptions();
		if (o.getGraphicalReservation()!=null) {
			oJ.setGraficalReservation(o.getGraphicalReservation().getName());
		}
		if (o.getPreferences() != null && !o.getPreferences().isEmpty()) {
			ArrayList<ReservationOptionGroupDef> rolJ = new ArrayList<ReservationOptionGroupDef>();
			
			for (ReservationPreferenceGroup p :  o.getPreferences()) {
				rolJ.add(convertToJson(p));
			}
			oJ.setPreferences(rolJ);
		
		}
		oJ.setServiceBrands(convertServiceBrandsToJson(o.getServiceBrands()));

		return oJ;
	}




	private static ReservationOptionGroupDef convertToJson(ReservationPreferenceGroup p) {
		
		ReservationOptionGroupDef oJ = new ReservationOptionGroupDef();
		oJ.setPreferenceGroup(p.getGroup());
		
		if (p.getPreference() != null && !p.getPreference().isEmpty()) {
			
			ArrayList<String> pL = new ArrayList<String>();
			
			for (String s: p.getPreference()) {
				pL.add(s);
			}
			oJ.setPreference(pL);
		}
		
		return oJ;
	}




	private static ReservationParams9181 convertToJson(Gtm.ReservationParams9181 p) {
		if (p == null) return null;
		ReservationParams9181 pJ = new ReservationParams9181();
		
		pJ.setBerthType(p.getBerthType().getName());
		pJ.setCoachTypeCode(Integer.toString(p.getCoachType()));
		if (p.getBerthType()!=null) {
			pJ.setCompartmentTypeCode(p.getBerthType().getName());
		}
		if (p.getService()!=null) {
			pJ.setServiceCode(p.getService().getName());
		}
		if (p.getServiceLevel()!=null) {
			pJ.setServiceLevelCode(p.getServiceLevel().getName());
		}
		pJ.setTariff(Integer.toString(p.getTariff()));
		
		if (p.getTravelClass()!=null) {
			pJ.setTravelClass(p.getTravelClass().getName());
		}
		return pJ;
	}




	private static List<SalesAvailabilityConstraintDef> convertSalesAvailabilityConstraints(SalesAvailabilityConstraints list){
			
			if (list.getSalesAvailabilityConstraints().isEmpty()) return null;
			ArrayList<SalesAvailabilityConstraintDef> listJson = new ArrayList<SalesAvailabilityConstraintDef>();
			for (SalesAvailabilityConstraint element: list.getSalesAvailabilityConstraints()) {
				listJson.add(convertToJson(element));
			}
			return listJson;
	}




	private static SalesAvailabilityConstraintDef convertToJson(SalesAvailabilityConstraint sa) {
		
		SalesAvailabilityConstraintDef saJ = new SalesAvailabilityConstraintDef();
		saJ.setId(sa.getId());
		
		if (sa.getRestrictions() != null && !sa.getRestrictions().isEmpty()) {
			
			ArrayList<gtm.SalesRestriction> listJ = new ArrayList<gtm.SalesRestriction>();
			for (SalesRestriction  r: sa.getRestrictions()) {
				listJ.add(convertToJson(r));
			}
			saJ.setSalesRestrictions(listJ);
		}
		return saJ;
	}




	private static gtm.SalesRestriction convertToJson(SalesRestriction r) {
		gtm.SalesRestriction rJ = new gtm.SalesRestriction();
		
		rJ.setEndOfSale(convertToJson(r.getEndOfSale()));
		rJ.setSalesDates(convertToJson(r.getSalesDates()));
		rJ.setStartOfSale(convertToJson(r.getStartOfSale()));

		return rJ;
	}




	private static List<RegionalConstraintDef> convertRegionalConstraints(RegionalConstraints list) {
		if (list.getRegionalConstraints().isEmpty()) return null;
		ArrayList<RegionalConstraintDef> listJson = new ArrayList<RegionalConstraintDef>();
		for (RegionalConstraint element: list.getRegionalConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static RegionalConstraintDef convertToJson(RegionalConstraint r) {
		
		RegionalConstraintDef rJ = new RegionalConstraintDef();
		rJ.setId(r.getId());
		rJ.setDistance(r.getDistance());
		if (r.getEntryConnectionPoint() != null) {
			rJ.setEntryConnectionPointId(r.getEntryConnectionPoint().getId());
		}
		if (r.getExitConnectionPoint() != null) {
			rJ.setExitConnectionPointId(r.getExitConnectionPoint().getId());
		}
		if (r.getRegionalValidity()!= null && !r.getRegionalValidity().isEmpty()) {
			
			ArrayList<RegionalValidityDef> rvlJ  = new ArrayList<RegionalValidityDef>();
			
			for (RegionalValidity rv: r.getRegionalValidity()) {				
				rvlJ.add(convertToJson(rv));							
			}
			rJ.setRegionalValidity(rvlJ);
		}
		return rJ;
	}




	private static RegionalValidityDef convertToJson(RegionalValidity rv) {
		
		RegionalValidityDef rvJ = new RegionalValidityDef();
		rvJ.setSeqNb(rv.getSeqNb());
		rvJ.setViaStations(convertToJson(rv.getViaStation()));
		rvJ.setLine(convertToJson(rv.getLine()));
		rvJ.setZone(convertToJson(rv.getZone()));
		rvJ.setPolygone(convertToJson(rv.getPolygone()));
		
		return rvJ;
	}




	private static PolygoneDef convertToJson(Polygone p) {
		
		PolygoneDef pJ = new PolygoneDef();
		
		if (p.getEdge()!= null && !p.getEdge().isEmpty()){
			
			ArrayList<GeoCoordinateDef> listJ = new ArrayList<GeoCoordinateDef>();
			
			for ( Edge e : p.getEdge()) {
				listJ.add(convertToJson(e));
			}
					
			pJ.setEdge(listJ);
		}
		
		return pJ;
	}




	private static GeoCoordinateDef convertToJson(Edge e) {
		GeoCoordinateDef gJ = new GeoCoordinateDef();
		
		gJ.setAccuracy(e.getAccuracy().getName());
		gJ.setLatitude(e.getLatitude());
		gJ.setLongitude(e.getLongitude());
		gJ.setSystem(GeoCoordinateDef.System.fromValue(e.getSystem().getName()));
		gJ.setUnit(GeoCoordinateDef.GeoCoordinateUnitDef.fromValue(e.getUnit().getName()));

		return gJ;
	}




	private static ZoneDef convertToJson(Zone z) {
		
		ZoneDef zJ = new ZoneDef();
		if (z.getBinaryZoneId()!= null && z.getBinaryZoneId().length > 0) {
			zJ.setBinaryZoneId(Base64.encode(z.getBinaryZoneId()));
		}
		if (z.getCarrier()!=null) {
			zJ.setCarrier(z.getCarrier().getCode());
		}
		zJ.setCity(z.getCity());
		if (z.getEntryStation()!= null) {
			zJ.setEntryStation(convertToJson(z.getEntryStation()));
		}
		if (z.getNutsCode()!=null) {
			zJ.setNutsCode(z.getNutsCode().getCode());
		}
		if (z.getTerminalStation()!= null) {
			zJ.setTerminalStation(convertToJson(z.getTerminalStation()));
		}
		if (z.getZoneDefinitions()!= null && !z.getZoneDefinitions().isEmpty()) {
			
			ArrayList<Integer> zoneIds = new ArrayList<Integer>();
			ArrayList<String> zoneDefinitionIds = new ArrayList<String>();
			
			for ( ZoneDefinition zd : z.getZoneDefinitions()) {
				if (zd.getId() != null) {
					zoneDefinitionIds.add(zd.getId());
				}
				if (zd.getZoneId() != 0) {
					zoneIds.add(zd.getZoneId());
				}
			}
			zJ.setZoneId(zoneIds);
			zJ.setZoneDefinitionIds(zoneDefinitionIds);

		}

		return zJ;
	}




	private static LineDef convertToJson(Line z) {
		LineDef zJ = new LineDef();
		if (z.getBinaryZoneId()!= null && z.getBinaryZoneId().length > 0) {
			zJ.setBinaryZoneId(Base64.encode(z.getBinaryZoneId()));
		}
		if (z.getCarrier()!=null) {
			zJ.setCarrier(z.getCarrier().getCode());
		}
		zJ.setCity(z.getCity());
		if (z.getEntryStation()!= null) {
			zJ.setEntryStation(convertToJson(z.getEntryStation()));
		}
		if (z.getNutsCode()!=null) {
			zJ.setNutsCode(z.getNutsCode().getCode());
		}
		if (z.getTerminalStation()!= null) {
			zJ.setTerminalStation(convertToJson(z.getTerminalStation()));
		}
		if (z.getLineId()!= null && !z.getLineId().isEmpty()) {
			
			ArrayList<String> lineIds = new ArrayList<String>();
			
			for (String id : z.getLineId()) {
				lineIds.add(id);
			}
			zJ.setLineId(lineIds);

		}

		return zJ;
	}




	private static ViaStationsDef convertToJson(ViaStation v) {
		
		ViaStationsDef vJ = new ViaStationsDef();
		if (v.getCarrier()!= null) {
			vJ.setCarrier(v.getCarrier().getCode());
		}
		
		vJ.setAlternativeRoute(convertToJson(v.getAlternativeRoutes()));
		vJ.setIsBorder(false);
		vJ.setRoute(convertToJson(v.getRoute()));
		vJ.setStation(convertToJson(v.getStation()));

		return vJ;
	}




	private static List<ViaStationsDef> convertToJson(Route r) {
		if (r == null || r.getStations() == null || r.getStations().isEmpty()) return null;
		
		ArrayList<ViaStationsDef> listJ = new ArrayList<ViaStationsDef>();
		
		for ( ViaStation s : r.getStations()) {
			listJ.add(convertToJson(s));
		}
		
		return listJ;
	}




	private static List<ViaStationsDef> convertToJson(EList<AlternativeRoute> ar) {
		
		if (ar == null || ar.isEmpty()) return null;
		
		ArrayList<ViaStationsDef>  arlJ = new ArrayList<ViaStationsDef>(); 
		
		for ( AlternativeRoute r : ar) {
			
			if (r.getStations() != null && r.getStations().isEmpty()) {
				
				ViaStationsDef arJ = new ViaStationsDef();
				ArrayList<ViaStationsDef>  arslJ = new ArrayList<ViaStationsDef>(); 
				arJ.setAlternativeRoute(arslJ);
				
				for (ViaStation s : r.getStations()) {
					arslJ.add(convertToJson(s));
				}
				
				arlJ.add(arJ);			
			}

		}

		return arlJ;
	}




	private static List<ReductionConstraintDef> convertReductionConstraints(ReductionConstraints list) {
		if (list.getReductionConstraints().isEmpty()) return null;
		ArrayList<ReductionConstraintDef> listJson = new ArrayList<ReductionConstraintDef>();
		for (ReductionConstraint element: list.getReductionConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static ReductionConstraintDef convertToJson(ReductionConstraint rc) {
		
		ReductionConstraintDef rcJ = new ReductionConstraintDef();
		
		rcJ.setId(rc.getId());
		
		if (rc.getRequiredReductionCards() != null && !rc.getRequiredReductionCards().isEmpty()) {
			ArrayList<RequiredCard> listJ = new ArrayList<RequiredCard>();
			
			for (RequiredReductionCard e : rc.getRequiredReductionCards()) {
				
				RequiredCard eJ = new RequiredCard();
				if (e.getCardClass() != null) {
					eJ.setCardClassId(e.getCardClass().getId().getName());
				}
				
				if (e.getCard() != null) {
					eJ.setCardId(e.getCard().getId());
				}
				eJ.setCardName(e.getName());
				
			}

			rcJ.setRequiredCards(listJ);
		}

		return rcJ;
	}




	private static List<ReductionCardDef> convertReductionCards(ReductionCards list) {
		if (list.getReductionCards().isEmpty()) return null;
		ArrayList<ReductionCardDef> listJson = new ArrayList<ReductionCardDef>();
		for (ReductionCard element: list.getReductionCards()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}



	private static ReductionCardDef convertToJson(ReductionCard card) {
		
		ReductionCardDef cardJ = new ReductionCardDef();
		
		cardJ.setCardId(card.getId());
		cardJ.setCardIssuer(card.getCardIssuer().getCode());
		
		if (card.getServiceClasses()!= null && !card.getServiceClasses().isEmpty()) {
			
			ArrayList<ServiceClassDef> listJ = new ArrayList<ServiceClassDef>();
			
			for (ServiceClass sc : card.getServiceClasses()) {
				listJ.add(convertToJson(sc));
			}
			
			cardJ.setServiceClasses(listJ);
		}

		
		if (card.getName() != null) {
			cardJ.setCardName(convertToJson(card.getName()));
		}

		return cardJ;
	}




	private static List<PriceDef> convertPrices(Prices list) {
		if (list.getPrices().isEmpty()) return null;
		ArrayList<PriceDef> listJson = new ArrayList<PriceDef>();
		for (Price element: list.getPrices()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static List<PersonalDataConstraintDef> convertPersonalDataConstraints(PersonalDataConstraints list) {
		if (list.getPersonalDataConstraints().isEmpty()) return null;
		ArrayList<PersonalDataConstraintDef> listJson = new ArrayList<PersonalDataConstraintDef>();
		for (PersonalDataConstraint element: list.getPersonalDataConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static PersonalDataConstraintDef convertToJson(PersonalDataConstraint pc) {
		
		PersonalDataConstraintDef pcJ = new PersonalDataConstraintDef();
		
		pcJ.setId(pc.getId());
		
		pcJ.setRequiredData(convertRequiredPersonalDataToJson(pc.getRequiredPersonalData()));
		
		pcJ.setAllowedChanges(convertAllowedPersonalDataChangesToJson(pc.getAllowedChanges()));

		return pcJ;
	}




	private static List<AllowedChange> convertAllowedPersonalDataChangesToJson(EList<AllowedPersonalDataChanges> ac) {
		if (ac == null || ac.isEmpty()) return null;

		ArrayList<AllowedChange> listJ = new ArrayList<AllowedChange>();
		
		for (AllowedPersonalDataChanges apc : ac) {
			
			AllowedChange acJ = new AllowedChange();
			acJ.setAcceptedReason(apc.getAcceptedReason().getName());
			acJ.setTimeLimit((float) apc.getTimeLimit());
			
		}

		return listJ;
	}




	private static List<RequiredDatum> convertRequiredPersonalDataToJson(EList<RequiredPersonalData> rpdl) {
		if (rpdl == null || rpdl.isEmpty()) return null;
		
		ArrayList<RequiredDatum> listJ = new ArrayList<RequiredDatum>();
		for (RequiredPersonalData  rpd : rpdl) {
			
			RequiredDatum it = new RequiredDatum();
			it.setCrossBorder(convertCrossBorderConditionToJson(rpd.getCrossBorder()));
			it.setDataItem(rpd.getDataItem().getName());
			it.setFulfillmentType(convertFulFillmentTypesToJson(rpd.getFulfillmentType()));
			it.setTicketHolderOnly(rpd.isTicketHolderOnly());
			it.setTransfer(convertPersonalDataTransferToJson(rpd.getTransfer()));
			
			
		}

		return listJ;
	}




	private static List<String> convertPersonalDataTransferToJson(EList<PersonalDataTransferType> trl) {
		if (trl == null || trl.isEmpty()) return null;
			
		ArrayList<String> listJ = new ArrayList<String>();
		
		for (PersonalDataTransferType t : trl) {
			listJ.add(t.getName());
		}

		return listJ;
	}



	private static List<String> convertFulFillmentTypesToJson(EList<FulfillmentType> ftl) {
		
		if (ftl == null || ftl.isEmpty()) return null;
		ArrayList<String> listJ = new ArrayList<String>();
		for (FulfillmentType f : ftl) {
			listJ.add(f.getName());
		}
		return listJ;
	}




	private static List<CrossBorderCondition> convertCrossBorderConditionToJson(EList<Gtm.CrossBorderCondition> cbl) {
		if (cbl == null || cbl.isEmpty()) return null;
		
		ArrayList<CrossBorderCondition> cblJ = new ArrayList<CrossBorderCondition>();
		
		for (Gtm.CrossBorderCondition c : cbl) {
			
			CrossBorderCondition cJ = new CrossBorderCondition();
			cJ.setAffectedServiceBrands(convertServiceBrandsToJson(c.getAffectedServiceBrands()));
			cJ.setFromCountry(c.getFromCountry().getISOcode());
			cJ.setToCountry(c.getToCountry().getISOcode());
			
		}
		return cblJ;
	}




	private static List<PassengerConstraintDef> convertPassengerConstraints(PassengerConstraints list) {
		if (list.getPassengerConstraints().isEmpty()) return null;
		ArrayList<PassengerConstraintDef> listJson = new ArrayList<PassengerConstraintDef>();
		for (PassengerConstraint element: list.getPassengerConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static PassengerConstraintDef convertToJson(PassengerConstraint pass) {
		
		PassengerConstraintDef passJ = new PassengerConstraintDef();
		
		passJ.setId(pass.getId());
		passJ.setAgeLimitForReservation(pass.getReservationAgeLimit()); 
		passJ.setAgeLimitToTravelAlone(pass.getTravelAloneAgeLimit()); 
		
		if (pass.getIncludedFreePassengers()!= null && !pass.getIncludedFreePassengers().getIncludedFreePassengers().isEmpty()) {
			ArrayList<IncludedFreePassenger> listJ = new ArrayList<IncludedFreePassenger>();
			
			for (PassengerCombinationConstraint freeP : pass.getIncludedFreePassengers().getIncludedFreePassengers()) {
				
				IncludedFreePassenger freeJ = new IncludedFreePassenger();
				
				freeJ.setNumber(freeP.getMaxNumber());
				if (freeP.getPassengerType() != null) {
					freeJ.setPassengerTypeRef(freeP.getPassengerType().getName());
				}
				
				listJ.add(freeJ);
			}
			
			passJ.setIncludedFreePassenger(listJ);
		}

		if (pass.getExcludedPassengerCombinations() != null && !pass.getExcludedPassengerCombinations().isEmpty()) {
			ArrayList<CombinationConstraint> listJ = new ArrayList<CombinationConstraint>();
			
			for (PassengerCombinationConstraint freeP : pass.getExcludedPassengerCombinations()) {
				
				CombinationConstraint freeJ = new CombinationConstraint();
				
				freeJ.setMaxNumber(freeP.getMaxNumber());
				if (freeP.getPassengerType() != null) {
					freeJ.setPassengerTypeRef(freeP.getPassengerType().getName());
				}
				
				listJ.add(freeJ);
			}
			
			passJ.setCombinationConstraint(listJ);
		}
		
		passJ.setIsAncilliaryItem(pass.isIsAncilliary());
		passJ.setLowerAgeLimit(pass.getLowerAgeLimit());
		passJ.setMaxWeightedPasseners(pass.getMaxTotalPassengerWeight());
		passJ.setMinWeightedPassengers(pass.getMinTotalPassengerWeight());
		if (pass.getText()!= null) {
			passJ.setNameRef(pass.getText().getId());
		}
		if (pass.getTravelerType() != null) {
			passJ.setPassengerType(pass.getTravelerType().getName());
		}
		
		passJ.setPassengerWeight(pass.getPassengerWeight());
		passJ.setUpperAgeLimit(pass.getUpperAgeLimit()); 
		
		return passJ;
	}




	private static List<FullfillmentConstraintDef> convertFullfillmentConstraints(FulfillmentConstraints list) {
		if (list.getFulfillmentConstraints().isEmpty()) return null;
		ArrayList<FullfillmentConstraintDef> listJson = new ArrayList<FullfillmentConstraintDef>();
		for (FulfillmentConstraint element: list.getFulfillmentConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static FullfillmentConstraintDef convertToJson(FulfillmentConstraint fc) {
		
		FullfillmentConstraintDef fcJ = new FullfillmentConstraintDef();
		fcJ.setId(fc.getId());
		
		if (fc.getAcceptedBarcodes() != null && fc.getAcceptedBarcodes().getAcceptedBarcodes() != null && !fc.getAcceptedBarcodes().getAcceptedBarcodes().isEmpty()) {
			fcJ.setAcceptedBarCodes(convertBarCodeTypes(fc.getAcceptedBarcodes().getAcceptedBarcodes()));
		}
		fcJ.setAcceptedFullfillmentTypes(convertFulFillmentTypesToJson(fc.getAcceptedFulfilmentTypes()));
		fcJ.setIndividualTicketingPermitted(fc.isIndividualTicketingPermitted());
		
		if (fc.getRequiredBarcodes() != null && fc.getRequiredBarcodes().getRequiredBarcodes() != null && !fc.getRequiredBarcodes().getRequiredBarcodes().isEmpty()) {
			fcJ.setRequiredBarCodes(convertBarCodeTypes(fc.getRequiredBarcodes().getRequiredBarcodes()));
		}
		fcJ.setRequiredSiS(convertControlDataExchangeTypesToJson(fc.getRequiredControlDataExchange()));
		
		return fcJ;
	}


	private static List<String> convertBarCodeTypes(EList<BarcodeTypes> el) {
		if (el == null || el.isEmpty()) return null;
		ArrayList<String> listJ = new ArrayList<String>();
		for (Enumerator e : el) {	listJ.add(e.getName());	}
		return listJ;
	}




	private static List<String> convertControlDataExchangeTypesToJson(EList<ControlDataExchangeTypes> el) {
		if (el == null || el.isEmpty()) return null;
		ArrayList<String> listJ = new ArrayList<String>();
		for (Enumerator e : el) {	listJ.add(e.getName());	}
		return listJ;
	}




	private static FareResourceLocationDef convertFareResourceLocation(FareResourceLocations rl) {
		
		FareResourceLocationDef rlJ = new FareResourceLocationDef();
		rlJ.setCarrierLocations(convertResourceCarrierLocations(rl.getCarrierResourceLocations()));
		rlJ.setStationLocations(convertStationResourceLocations(rl.getStationResourceLocations()));
		rlJ.setTrainLocations(convertTrainResourceLocations(rl.getTrainResourceLocations()));

		return rlJ;
	}




	private static List<TrainResourceLocationDef> convertTrainResourceLocations(TrainResourceLocations l) {
		if (l == null || l.getTrainResourceLocations().isEmpty()) return null;
		
		ArrayList<TrainResourceLocationDef> lJ = new ArrayList<TrainResourceLocationDef>();
		
		for (TrainResourceLocation t : l.getTrainResourceLocations()) {
			lJ.add(convertToJson(t));
		}
		return lJ;
	}



	private static TrainResourceLocationDef convertToJson(TrainResourceLocation t) {
		
		TrainResourceLocationDef trJ = new TrainResourceLocationDef();
		if (t.getCarrier()!= null) {
			trJ.setCarrier(t.getCarrier().getCode());
		}
		trJ.setTrainId(t.getTrainId());
		
		trJ.setOnlineResource(convertOnlineResourcesToJson(t.getOnlineResources()));
		
		return trJ;
	}




	private static List<OnlineResourceDef> convertOnlineResourcesToJson(EList<OnlineResource> rl) {
		if (rl == null || rl.isEmpty()) return null;
		
		ArrayList<OnlineResourceDef> lJ = new ArrayList<OnlineResourceDef>();
		
		for (OnlineResource r : rl){
			OnlineResourceDef rJ = new OnlineResourceDef();
			if (r.getInterfaceType() !=null) {
				rJ.setInterfaceType(r.getInterfaceType().getName());
			}
			if (r.getOfferRequestType()!=null) {
				rJ.setOfferType(r.getOfferRequestType().getName());
			}
			rJ.setSystem(r.getSystem());
			rJ.setVersion(r.getVersion());
			
			lJ.add(rJ);
		}

		return lJ;
	}




	private static List<StationResourceLocationDef> convertStationResourceLocations(StationResourceLocations l) {
		if (l == null || l.getStationResourceLocations().isEmpty()) return null;
		
		ArrayList<StationResourceLocationDef> lJ = new ArrayList<StationResourceLocationDef>();
		
		for (StationResourceLocation t : l.getStationResourceLocations()) {
			lJ.add(convertToJson(t));
		}
		
		return lJ;
	}




	private static StationResourceLocationDef convertToJson(StationResourceLocation t) {
		StationResourceLocationDef srJ = new StationResourceLocationDef();
		
		srJ.setOnlineResource(convertOnlineResourcesToJson(t.getOnlineResources()));
		
		if (t.getConnectionPoints()!= null && !t.getConnectionPoints().isEmpty()) {
			
			ArrayList<String> lJ = new ArrayList<String>();
			
			for (ConnectionPoint p : t.getConnectionPoints()) {
				lJ.add(p.getId());
			}
			
			srJ.setConnectionPointIds(lJ);
			
		}
		

		
		srJ.setStations(convertStationsToJson(t.getStations()));

		return srJ;
	}




	private static List<CarrierResourceLocationDef> convertResourceCarrierLocations(CarrierResourceLocations l) {
		if (l == null || l.getCarrierResourceLocations().isEmpty()) return null;
		
		ArrayList<CarrierResourceLocationDef> lJ = new ArrayList<CarrierResourceLocationDef>();
		
		for (CarrierResourceLocation t : l.getCarrierResourceLocations()) {
			lJ.add(convertToJson(t));
		}
		
		return lJ;
	}




	private static CarrierResourceLocationDef convertToJson(CarrierResourceLocation t) {
		
		CarrierResourceLocationDef lJ = new CarrierResourceLocationDef();
		
		if (t.getCarrier()!= null) {
			lJ.setCarrier(t.getCarrier().getCode());
		}
		if (t.getServiceBrand()!= null) {
			lJ.setServiceBrandCode(t.getServiceBrand().getCode());
		}
		lJ.setOnlineResource(convertOnlineResourcesToJson(t.getOnlineResources()));
		
		return lJ;
	}




	private static List<FareElementDef> convertFareElements(FareElements list) {

		if (list.getFareElements().isEmpty()) return null;
		ArrayList<FareElementDef> listJson = new ArrayList<FareElementDef>();
		for (FareElement element: list.getFareElements()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static FareElementDef convertToJson(FareElement fare) {
		
		FareElementDef fareJ = new FareElementDef();
		
		fareJ.setId(fare.getId());
		
		if (fare.getType() != null) {
			fareJ.setFareType(fare.getType().getName());
		}
		
		if (fare.getAfterSalesRule() != null) {
			fareJ.setAfterSalesRulesRef(fare.getAfterSalesRule().getId());
		}
		if (fare.getCarrierConstraint()!= null) {
			fareJ.setCarrierConstraintRef(fare.getCarrierConstraint().getId());
		}
		
		if (fare.getCombinationConstraint()!= null) {
			fareJ.setCombinationConstraintRef(fare.getCombinationConstraint().getId());
		}
		
		if (fare.getFareDetailDescription() != null){
			fareJ.setFareDetailDescriptionRef(fare.getFareDetailDescription().getId());
		}
		
		if (fare.getFulfillmentConstraint() != null) {
			fareJ.setFullfillmentConstraintRef(fare.getFulfillmentConstraint().getId());
		}
		
		if (fare.getLegacyAccountingIdentifier()!= null) {
			
		
			LegacyAccountingIdentifier lai = new LegacyAccountingIdentifier();
			
			lai.setSerialId(fare.getLegacyAccountingIdentifier().getSeriesId());
			lai.setTariffId(fare.getLegacyAccountingIdentifier().getTariffId());
			lai.setAddId(fare.getLegacyAccountingIdentifier().getAddSeriesId());			
			
			fareJ.setLegacyAccountingIdentifier(lai);
			
		}
		
		if (fare.getText() != null) {
			fareJ.setNameRef(fare.getText().getId());
		}
		
		if (fare.getPassengerConstraint() != null) {
			fareJ.setPassengerConstraintRef(fare.getPassengerConstraint().getId());
		}
		
		if (fare.getPersonalDataConstraint()!= null) {
			fareJ.setPersonalDataConstraintRef(fare.getPersonalDataConstraint().getId());
		}
		
		if (fare.getPrice() != null) {
			fareJ.setPriceRef(fare.getPrice().getId());
		}
		
		if (fare.getReductionConstraint()!= null) {
			fareJ.setReductionConstraintRef(fare.getReductionConstraint().getId());
		}
		
		if (fare.getRegionalConstraint()!= null) {
			fareJ.setRegionalConstraintRef(fare.getRegionalConstraint().getId());
		}
		
		if (fare.getReservationParameter() != null) {
			fareJ.setReservationParameterRef(fare.getReservationParameter().getId());
		}
		
		if (fare.getSalesAvailability()!=null) {
			fareJ.setSalesAvailabilityConstraintRef(fare.getSalesAvailability().getId());
		}
		
		if (fare.getTravelValidity() != null) {
			fareJ.setTravelValidityConstraintRef(fare.getTravelValidity().getId());
		} 
		
		if (fare.getServiceClass()!= null) {
			fareJ.setServiceClassRef(fare.getServiceClass().getId().getName());
		}
		
		if (fare.getServiceConstraint()!=null) {
			fareJ.setServiceConstraintRef(fare.getServiceConstraint().getId());
		}
		
		if (fare.getServiceLevel()!=null) {
			fareJ.setServiceLevelRef(fare.getServiceLevel().getId());
		}
		
		return fareJ;
	}




	private static List<ConnectionPointDef> convertConnectionPoints(ConnectionPoints list) {
		if (list.getConnectionPoints().isEmpty()) return null;
		ArrayList<ConnectionPointDef> listJson = new ArrayList<ConnectionPointDef>();
		for (ConnectionPoint element: list.getConnectionPoints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static ConnectionPointDef convertToJson(ConnectionPoint cp) {
		
		ConnectionPointDef cpJ = new ConnectionPointDef();
		cpJ.setId(cp.getId());
		cpJ.setLegacyBorderPointCode(Integer.toString(cp.getLegacyBorderPointCode()));
		cpJ.setName(cp.getName());
		cpJ.setStationSets(convertStationSetsToJson(cp.getConnectedStationSets()));
		return cpJ;
	}




	private static List<List<StationDef>> convertStationSetsToJson(EList<StationSet> ssl) {
		if (ssl == null || ssl.isEmpty()) return null;
		
		ArrayList<List<StationDef>> listJ = new ArrayList<List<StationDef>>();
		
		for (StationSet set : ssl) {
			listJ.add(convertStationsToJson(set.getStations()));	
		}
		return listJ;
	}




	private static List<StationDef> convertStationsToJson(EList<Station> set) {
		if (set == null || set.isEmpty()) return null;
		
		ArrayList<StationDef> listJ = new ArrayList<StationDef>();
		for (Station s :  set) {
			listJ.add(convertToJson(s));
		}
		return null;
	}


	private static StationDef convertToJson(Station s) {
		StationDef sJ = new StationDef();
		sJ.setCountry(Integer.toString(s.getCountry().getCode()));
		sJ.setLocalCode(s.getCode());
		return sJ;
	}




	private static List<FareCombinationConstraintDef> convertCombinationConstraints(CombinationConstraints list) {
		if (list.getCombinationConstraints().isEmpty()) return null;
		ArrayList<FareCombinationConstraintDef> listJson = new ArrayList<FareCombinationConstraintDef>();
		for (Gtm.CombinationConstraint element: list.getCombinationConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static FareCombinationConstraintDef convertToJson(Gtm.CombinationConstraint fc) {
		FareCombinationConstraintDef fcJ = new FareCombinationConstraintDef();
		
		fcJ.setId(fc.getId());
		
		fcJ.setCombinationModels(convertCombinationModelsToJson(fc.getCombinationModels()));
		
		return fcJ;
	}





	private static List<FareCombinationModelDef> convertCombinationModelsToJson(EList<FareCombinationModel> cmL) {
		if (cmL == null || cmL.isEmpty()) return null;
		
		ArrayList<FareCombinationModelDef> listJ = new ArrayList<FareCombinationModelDef>();
		
		for (FareCombinationModel m : cmL) {
		
			FareCombinationModelDef fcJ = new FareCombinationModelDef();
			
			fcJ.setAllowedAllocators(convertCarriersToJson(m.getAllowedAllocators()));
			fcJ.setAllowedClusters(convertClusterListToJson(m.getAllowedClusters()));
			fcJ.setAllowedCommonContracts(convertCarriersToJson(m.getAllowedCommonContracts()));
			fcJ.setCombinableCarrier(convertCarriersToJson(m.getCombinableCarriers()));
			fcJ.setModel(m.getModel().getName());
			fcJ.setOnlyWhenCombined(m.isOnlyWhenCombined());
			fcJ.setReferenceCluster(m.getReferenceCluster().getName());
		
			
			listJ.add(fcJ);
		
		}
		return listJ;
	}




	private static List<String> convertClusterListToJson(EList<Clusters> acs) {
		
		if (acs == null || acs.isEmpty()) return null;
		ArrayList<String> acJ = new ArrayList<String>();
		for (Clusters c : acs) {
			acJ.add(c.getName());
		}
		return acJ;
	}




	private static List<CarrierConstraintDef> convertCarrierConstraints(CarrierConstraints list) {
		if (list.getCarrierConstraints().isEmpty()) return null;
		ArrayList<CarrierConstraintDef> listJson = new ArrayList<CarrierConstraintDef>();
		for (CarrierConstraint element: list.getCarrierConstraints()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static List<CalendarDef> convertCalendars(Calendars list) {
		if (list.getCalendars().isEmpty()) return null;
		ArrayList<CalendarDef> listJson = new ArrayList<CalendarDef>();
		for (Calendar element: list.getCalendars()) {
			listJson.add(convertToJson(element));
		}
		return listJson;
	}




	private static CalendarDef convertToJson(Calendar cal) {
		
		CalendarDef calJ = new CalendarDef();
		calJ.setId(cal.getId());
		calJ.setUTCoffset(cal.getUtcOffset());		
		
		calJ.setFromDate(jsondf.format(cal.getFromDate()));
		calJ.setUntilDate(jsondf.format(cal.getUntilDate()));		
		
		if (cal.getDates() != null && !cal.getDates().isEmpty()) {
			ArrayList<String> days = new ArrayList<String>();
			for (Date date : cal.getDates()) {
				days.add(jsondf.format(date));
			}
			calJ.setDates(days);
		}
			
		return calJ;
	}




	private static List<AfterSalesRulesDef> convertAfterSalesConditionsToJson(AfterSalesRules afterSalesRules) {
		
		if (afterSalesRules.getAfterSalesRules().isEmpty()) return null;
		
		ArrayList<AfterSalesRulesDef> result = new ArrayList<AfterSalesRulesDef>();

		
		for (AfterSalesRule rule :  afterSalesRules.getAfterSalesRules()) {
			AfterSalesRulesDef ruleJ = new AfterSalesRulesDef();
			ruleJ.setId(rule.getId());
			
			for (AfterSalesCondition cond : rule.getConditions()) {
				
				AfterSalesConditionDef condJ = new AfterSalesConditionDef();
				
				condJ.setCarrierFee(cond.isCarrierFee());
				condJ.setIndividualContracts(cond.isIndividualContracts());
				condJ.setTransactionType(cond.getTransactionType().getName());
				
				condJ.setApplicationTime(convertToJson(cond.getApplicationTime()));
												
				condJ.setFeeId(cond.getFee().getId());
				ruleJ.getAfterSalesRules().add(condJ);
				
			}

			result.add(ruleJ);
		}

		return result;
	}
	
	private static RelativeTimeDef convertToJson(RelativeTime rt) {
		if (rt == null) return null;
		
		RelativeTimeDef rtJ = new RelativeTimeDef();
		rtJ.setTimeUnit(rt.getUnit().getName());
		rtJ.setTimeReference(rt.getReference().getName());
		rtJ.setTimeValue(rt.getValue());
			
		return rtJ;
	}




	private static CarrierConstraintDef convertToJson(CarrierConstraint constraint) {
		
		CarrierConstraintDef constraintDef = new CarrierConstraintDef();
		
		constraintDef.setId(constraint.getId());
		
		if (constraint.getIncludedCarriers() != null && !constraint.getIncludedCarriers().isEmpty()) {
			
			ArrayList<String> includedCarriers = new ArrayList<String>();
			for (Carrier carrier : constraint.getIncludedCarriers()) {
				includedCarriers.add(carrier.getCode());			
			}
			constraintDef.setIncludedCarrier(includedCarriers);

			
		} else if (constraint.getExcludedCarriers() != null && !constraint.getExcludedCarriers().isEmpty()) {
			
			ArrayList<String> excludedCarriers = new ArrayList<String>();
			for (Carrier carrier : constraint.getExcludedCarriers()) {
				excludedCarriers.add(carrier.getCode());			
			}
			constraintDef.setIncludedCarrier(excludedCarriers);
			
		}  
		
		return constraintDef;
	}
	
	private static PriceDef convertToJson(Price price) {
		
		PriceDef priceDef = new PriceDef();
		
		priceDef.setId(price.getId());
		
		ArrayList<CurrencyPriceDef> currencyList = new ArrayList<CurrencyPriceDef>();
		priceDef.setPrice(currencyList);
		
		for (CurrencyPrice cur : price.getCurrencies()) {
			
			CurrencyPriceDef curDef = new CurrencyPriceDef();
			
			curDef.setAmount(cur.getAmount());
			curDef.setCurrency(cur.getCurrency().getIsoCode());
			
			ArrayList<VatDetail> vatDefs = new ArrayList<VatDetail>();
									
			for ( VATDetail vat : cur.getVATdetails()) {
				
				VatDetail vatDef = new VatDetail();
				vatDef.setAmount(vat.getAmount());
				vatDef.setTaxId(vat.getTaxId());
				vatDef.setScope(vat.getScope().getName());
				vatDef.setPercentage(vat.getPercentage());
				vatDef.setCountry(vat.getCountry().getISOcode());
				vatDefs.add(vatDef);
			}
			
			currencyList.add(curDef);	
		}

		return priceDef;
		
	}
	

	private static Delivery convertDeliveryToJson(Gtm.Delivery idelivery) {
		Delivery delivery = new Delivery();
		delivery.setFareProvider(idelivery.getProvider().getCode());
		delivery.setDeliveryId(idelivery.getId());
		delivery.setOptionalDelivery(idelivery.isOptional());
		delivery.setReplacementDeliveryId(idelivery.getReplacedDeliveryId());
		delivery.setPreviousDeliveryId(idelivery.getPreviousDeliveryId());
		return delivery;
	}

}