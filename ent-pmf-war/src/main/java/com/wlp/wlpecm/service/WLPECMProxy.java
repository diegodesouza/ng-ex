package com.wlp.wlpecm.service;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.Holder;
import com.wellpoint.esb.header.v3.ESBHeaderType;

public class WLPECMProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.wlp.wlpecm.service.WLPECMFileNet _service = null;
        private com.wlp.wlpecm.service.WLPECM _proxy = null;
        private Dispatch<Source> _dispatch = null;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.wlp.wlpecm.service.WLPECMFileNet(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            _service = new com.wlp.wlpecm.service.WLPECMFileNet();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getWLPECM();
        }

        public com.wlp.wlpecm.service.WLPECM getProxy() {
            return _proxy;
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://service.wlpecm.wlp.com", "WLPECM");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

        public void setMTOMEnabled(boolean enable) {
            SOAPBinding binding = (SOAPBinding) ((BindingProvider) _proxy).getBinding();
            binding.setMTOMEnabled(enable);
        }
    }

    public WLPECMProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public WLPECMProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public GetRepositoryListResponse getRepositoryList(Holder<ESBHeaderType> header, GetRepositoryList parameters) throws Fault {
        return _getDescriptor().getProxy().getRepositoryList(header,parameters);
    }

    public UpdateContentMetadataResponse updateContentMetadata(Holder<ESBHeaderType> header, UpdateContentMetadata parameters) throws Fault {
        return _getDescriptor().getProxy().updateContentMetadata(header,parameters);
    }

    public GetRepositoryStatusResponse getRepositoryStatus(Holder<ESBHeaderType> header, GetRepositoryStatus parameters) throws Fault {
        return _getDescriptor().getProxy().getRepositoryStatus(header,parameters);
    }

    public GetObjectClassResponse getObjectClass(Holder<ESBHeaderType> header, GetObjectClass parameters) throws Fault {
        return _getDescriptor().getProxy().getObjectClass(header,parameters);
    }

    public GetListingResponse getListing(Holder<ESBHeaderType> header, GetListing parameters) throws Fault {
        return _getDescriptor().getProxy().getListing(header,parameters);
    }

    public GetContentResponse getContent(Holder<ESBHeaderType> header, GetContent parameters) throws Fault {
        return _getDescriptor().getProxy().getContent(header,parameters);
    }

    public StoreContentResponse storeContent(Holder<ESBHeaderType> header, StoreContent parameters) throws Fault {
        return _getDescriptor().getProxy().storeContent(header,parameters);
    }

}