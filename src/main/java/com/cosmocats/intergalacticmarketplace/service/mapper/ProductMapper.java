package com.cosmocats.intergalacticmarketplace.service.mapper;

import com.cosmocats.intergalacticmarketplace.dto.ProductDTO;
import com.cosmocats.intergalacticmarketplace.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
    List<ProductDTO> productsToProductDTOs(List<Product> products);
}
