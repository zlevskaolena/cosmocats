package com.cosmocats.intergalacticmarketplace.service.mapper;

import com.cosmocats.intergalacticmarketplace.dto.ProductDTO;
import com.cosmocats.intergalacticmarketplace.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}
