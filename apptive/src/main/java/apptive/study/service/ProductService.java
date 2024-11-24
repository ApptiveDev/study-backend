package apptive.study.service;

import apptive.study.domain.Product;
import apptive.study.dto.request.ProductRequest;
import apptive.study.exception.StudyException;
import apptive.study.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static apptive.study.exception.ErrorCode.PRODUCT_NAME_DUPLICATE_ERROR;
import static apptive.study.exception.ErrorCode.PRODUCT_NOT_FOUND_ERROR;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 등록하기
    @Transactional
    public void save(ProductRequest productRequest){
        Product product = new Product(productRequest.name(), productRequest.price());

        // 동일한 이름의 상품이 존재한다면, 예외 던지기
        if(productRepository.existsByName(product.getName())) {
            throw new StudyException(PRODUCT_NAME_DUPLICATE_ERROR);
        }

        productRepository.save(product);
    }

    // 이름으로 상품 찾기
    @Transactional(readOnly = true)
    public Product findByName(String name){
        Product product =  productRepository.findByName(name)
                .orElseThrow(()->  new StudyException(PRODUCT_NOT_FOUND_ERROR));
        return product;
    }

    // 모든 상품 목록 찾기
    @Transactional(readOnly = true)
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}