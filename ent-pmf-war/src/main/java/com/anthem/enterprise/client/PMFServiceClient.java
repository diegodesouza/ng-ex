package com.anthem.enterprise.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anthem.common.PMFResourceUtils;
import com.anthem.enterprise.client.http.HttpClientPool;
import com.anthem.enterprise.client.model.UploadDocRequest;
import com.anthem.enterprise.client.model.UploadDocResponse;
import com.anthem.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PMFServiceClient {
	private static final Logger log = LogManager.getLogger(PMFServiceClient.class);
	private Properties webConfigProp = null;

	public String postDataToPLM(String request, int pGI_ID) {
		webConfigProp = PMFResourceUtils.getProperties();
		HttpPost postRequest = new HttpPost(webConfigProp.getProperty(Constants.PROVIDER_PLM_SERVICE_ENDPOINT));
		try {
			StringEntity input = new StringEntity(request);
			input.setContentType(Constants.CONTENT_APPLICATION_JSON);
			postRequest.addHeader("apiKey", webConfigProp.getProperty(Constants.PROVIDER_APP_API_KEY));
			postRequest.addHeader("metaTransId", "PMF-" + String.valueOf(pGI_ID));
			postRequest.setEntity(input);
			CloseableHttpResponse response = HttpClientPool.getClient().execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity());

			return responseString;

		} catch (ClientProtocolException e) {
			log.error("Exception occured in while calling PLM Service :: " + e);
		} catch (IOException e) {
			log.error("Exception occured in while calling PLM Service :: " + e);
		}

		return null;

	}

	public String postDoc(UploadDocRequest uploadDoc) throws URISyntaxException {
		webConfigProp = PMFResourceUtils.getProperties();
		try {
			URIBuilder uriBuilder = new URIBuilder(
					webConfigProp.getProperty(Constants.PROVIDER_UPLOAD_DOC_SERVICE_ENDPOINT)
							+ Constants.PROVIDER_UPLOAD_DOC_SERVICE_REPOSITORY + "/"
							+ Constants.PROVIDER_UPLOAD_DOC_SERVICE_APPLICATIONID);

			log.info("URL for Upload Doc: " + uriBuilder.build().toString());
			HttpPost postRequest = new HttpPost(uriBuilder.build());
			postRequest.setHeader("Content-type", "application/json");
			postRequest.setHeader("apiKey", webConfigProp.getProperty(Constants.PROVIDER_APP_API_KEY));
			StringEntity stringEntity = new StringEntity(new GsonBuilder().disableHtmlEscaping().create().toJson(uploadDoc));
			postRequest.setEntity(stringEntity);
			CloseableHttpResponse response = HttpClientPool.getClient().execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity());
			UploadDocResponse uploadResponse = new GsonBuilder().disableHtmlEscaping().create().fromJson(responseString,
					UploadDocResponse.class);
			log.info("Response from Upload Client:" + responseString);
			log.info("Response DCN:" + uploadResponse.getAdditionalParams().getDcn());

			return uploadResponse.getAdditionalParams().getDcn();

		} catch (ClientProtocolException e) {
			log.error("Exception occured in while calling postDoc Service :: " + e);
		} catch (IOException e) {
			log.error("Exception occured in while calling postDoc Service :: " + e);
		} catch (Exception e) {
			log.error("Exception while parsing response postDoc Service :: " + e);
		}

		return null;

	}

}
