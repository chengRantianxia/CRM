package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.common.bean.Product;
import com.briup.crm.common.bean.ProductExample;
import com.briup.crm.common.bean.Storage;
import com.briup.crm.common.bean.StorageExample;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.dao.mappleInterface.ProductMapper;
import com.briup.crm.dao.mappleInterface.StorageMapper;
import com.briup.crm.service.interfaces.IBasicDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class IBasicDataServiceImpl implements IBasicDataService{
	
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private StorageMapper storageMapper;
	
	public PageInfo<Product> findProduct(Product product, int curPage, int row) throws CrmCommonException {
		PageHelper.startPage(curPage, row);
		ProductExample example = new ProductExample();
		example.createCriteria().andProdNameLike("%"+product.getProdName()+"%").andProdTypeLike("%"+product.getProdType()+"%").
		andProdBatchLike("%"+product.getProdBatch()+"%");
		List<Product> list = productMapper.selectByExample(example);
		PageInfo<Product> pageInfoProduct = new PageInfo<Product>(list);
		return pageInfoProduct;
	}

	public PageInfo<Product> findProductAll(int curPage, int row) throws CrmCommonException {
		PageHelper.startPage(curPage, row);
		ProductExample example = new ProductExample();
		List<Product> list = productMapper.selectByExample(example);
		PageInfo<Product> pageInfoProduct = new PageInfo<Product>(list);
		return pageInfoProduct;
	}

	public PageInfo<Storage> findStorageAll(int curpage, int row) throws CrmCommonException {
		PageHelper.startPage(curpage, row);
		StorageExample example = new StorageExample();
		List<Storage> list = storageMapper.selectByExample(example);
		PageInfo<Storage> pageInfoStorage = new PageInfo<Storage>(list);
		return pageInfoStorage;
	}

	public PageInfo<Storage> findStorage(Storage storage, int curpage, int row) throws CrmCommonException {
		PageHelper.startPage(curpage, row);
		StorageExample example = new StorageExample();
		example.createCriteria().andStkNameLike("%"+storage.getStkName()+"%").andStkWarehourseLike("%"+storage.getStkWarehourse()+"%");
		List<Storage> list = storageMapper.selectByExample(example);
		PageInfo<Storage> pageInfoStorage = new PageInfo<Storage>(list);
		return pageInfoStorage;
	}

}
