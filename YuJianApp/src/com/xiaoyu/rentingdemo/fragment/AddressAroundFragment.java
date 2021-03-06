package com.xiaoyu.rentingdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.util.Constants;
import com.xiaoyu.rentingdemo.util.StringUtil;
import com.xiaoyu.rentingdemo.util.ToastUtils;
import com.xiaoyu.rentingdemo.util.Utils;

/**
 * 查看周边设施
 * 
 * @author shwan
 * 
 */
public class AddressAroundFragment extends BaseFragment implements
		OnGetGeoCoderResultListener, OnGetPoiSearchResultListener,
		OnMarkerClickListener {

	private static final String TAG = AddressAroundFragment.class.getName();

	private Bundle bundle;
	private String cityName;
	private String villageName;

	private MapView mapView;
	private BaiduMap mBaiduMap;
	private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用

	private PoiSearch poiSearch;
	private NearbySearchInfo nearbySearchInfo;

	private RelativeLayout relativeLayoutMetro;
	private RelativeLayout relativeLayoutBus;
	private RelativeLayout relativeLayoutRestaurant;
	private RelativeLayout relativeLayoutHospital;
	private RelativeLayout relativeLayoutMarket;
	private RelativeLayout relativeLayoutEntertainment;

	private String searchStr = "";
	private GeoCodeResult geoCodeResult;
	private PoiResult poiResult;
	private int nowIcon;	// bottom layout click item

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle = getArguments();
		if (bundle == null) {
			return;
		}
		cityName = bundle.getString(Constants.KEY_POI_CITY);
		villageName = bundle.getString(Constants.KEY_POI_VILLAGE_NAME);
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		mSearch.geocode(new GeoCodeOption().city(cityName).address(villageName));

		// 注册检索
		poiSearch = PoiSearch.newInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layoutId = R.layout.fragment_address_around;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void findViewById(View rootView) {
		super.findViewById(rootView);
		// init map view
		mapView = (MapView) rootView.findViewById(R.id.mapview_address_around);
		mBaiduMap = mapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(17.0f);
		mBaiduMap.setMapStatus(msu);

		relativeLayoutBus = (RelativeLayout) rootView
				.findViewById(R.id.rl_address_around_bus);
		relativeLayoutEntertainment = (RelativeLayout) rootView
				.findViewById(R.id.rl_address_around_entertainment);
		relativeLayoutHospital = (RelativeLayout) rootView
				.findViewById(R.id.rl_address_around_hospital);
		relativeLayoutMarket = (RelativeLayout) rootView
				.findViewById(R.id.rl_address_around_market);
		relativeLayoutMetro = (RelativeLayout) rootView
				.findViewById(R.id.rl_address_around_metro);
		relativeLayoutRestaurant = (RelativeLayout) rootView
				.findViewById(R.id.rl_address_around_restaurant);

		setLinstener();
	}

	@Override
	public void initCommTop(View rootView) {
		super.initCommTop(rootView);
		relativeLayoutTopSearch.setVisibility(View.GONE);
		imageViewPersonal.setVisibility(View.GONE);
		textViewCity.setVisibility(View.GONE);
		textViewTitle.setVisibility(View.VISIBLE);
	}

	@Override
	public void setLinstener() {
		super.setLinstener();
		relativeLayoutBus.setOnClickListener(this);
		relativeLayoutEntertainment.setOnClickListener(this);
		relativeLayoutHospital.setOnClickListener(this);
		relativeLayoutMarket.setOnClickListener(this);
		relativeLayoutMetro.setOnClickListener(this);
		relativeLayoutRestaurant.setOnClickListener(this);
		poiSearch.setOnGetPoiSearchResultListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {

		case R.id.rl_address_around_metro:
			searchStr = getText(R.string.str_metro).toString();
			nowIcon = 0;
			break;
		case R.id.rl_address_around_bus:
			searchStr = getText(R.string.str_bus).toString();
			nowIcon = 1;
			break;
		case R.id.rl_address_around_restaurant:
			searchStr = getText(R.string.str_restaurant).toString();
			nowIcon = 2;
			break;
		case R.id.rl_address_around_hospital:
			searchStr = getText(R.string.str_hospital).toString();
			nowIcon = 3;
			break;
		case R.id.rl_address_around_market:
			searchStr = getText(R.string.str_market).toString();
			nowIcon = 4;
			break;
		case R.id.rl_address_around_entertainment:
			searchStr = getText(R.string.str_entertainment).toString();
			nowIcon = 5;
			break;
		default:
			break;
		}
		// TODO ADD FILE IN DRAWABLE
		changeBottomFocusable(v.getId());
		if (StringUtil.isNull(searchStr)) {
			return;
		}
		poiNearBySearch(searchStr);
	}

	/**
	 * 附近搜索
	 * 
	 * @param searchString
	 */
	private void poiNearBySearch(String searchString) {
		PoiNearbySearchOption searchOption = new PoiNearbySearchOption();
		if (geoCodeResult == null) {
			return;
		}
		searchOption.location(geoCodeResult.getLocation());
		searchOption.keyword(searchString);
		searchOption.pageNum(0);
		searchOption.radius(1000);// 搜索范围 1km
		searchOption.pageCapacity(10);
		poiSearch.searchNearby(searchOption);
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(getActivity(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap
				.addOverlay(new MarkerOptions().position(result.getLocation())
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.ic_mark)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		geoCodeResult = result;
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

	}

	@Override
	public void onPause() {
		mapView.onPause();
		super.onPause();
	}

	@Override
	public void onResume() {
		mapView.onResume();
		super.onResume();
	}

	@Override
	public void onDestroy() {
		mapView.onDestroy();
		mSearch.destroy();
		super.onDestroy();
	}

	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {
		if (result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(getActivity(),
					result.getName() + ": " + result.getAddress(),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * SET ICON OF OVERLAYER,AND ADD DETAIL CLICK LESTENER
	 */
	@SuppressWarnings("unused")
	@Override
	public void onGetPoiResult(PoiResult result) {
		searchStr = "";
		if (result == null
				|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
			ToastUtils.showToast("没有结果");
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			mBaiduMap.clear();
			mBaiduMap.addOverlay(new MarkerOptions().position(
					geoCodeResult.getLocation()).icon(
					BitmapDescriptorFactory.fromResource(R.drawable.ic_mark)));
			mBaiduMap.setMapStatus(MapStatusUpdateFactory
					.newLatLng(geoCodeResult.getLocation()));
			if (result == null) {
				return;
			}
			poiResult = result;
			// TODO UPDATE MAPICON
			for (int i = 0; i < result.getAllPoi().size(); i++) {
				PoiInfo poiInfo = result.getAllPoi().get(i);
				// overlay.removeFromMap();
				mBaiduMap.addOverlay(new MarkerOptions().position(
						poiInfo.location)
						.icon(BitmapDescriptorFactory.fromBitmap(Utils
								.readBitMap(getActivity(),
										Constants.MAP_ICON_ID[nowIcon]))));
				// overlay.addToMap();
			}
			mBaiduMap.setOnMarkerClickListener(this);
			return;
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

			// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
			String strInfo = "在";
			for (CityInfo cityInfo : result.getSuggestCityList()) {
				strInfo += cityInfo.city;
				strInfo += ",";
			}
			strInfo += "找到结果";
			Toast.makeText(getActivity(), strInfo, Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * update bottom layout show
	 * 
	 * @param viewId
	 */
	private void changeBottomFocusable(int viewId) {

	}

	@SuppressWarnings("unused")
	@Override
	public boolean onMarkerClick(Marker marker) {
		final LatLng ll = marker.getPosition();
		PoiInfo info = null;
		for (PoiInfo poiInfo : poiResult.getAllPoi()) {
			if (poiInfo.location == ll) {
				info = poiInfo;
			}
		}
		if (info == null) {
			return false;
		}
		poiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUid(info.uid));
		return true;
	}
}
