package com.pn.controller;

import com.pn.entity.*;
import com.pn.exception.BusinessException;
import com.pn.mapper.BrandMapper;
import com.pn.mapper.SupplyMapper;
import com.pn.page.Page;
import com.pn.service.*;
import com.pn.utils.TokenUtils;
import com.pn.utils.WarehouseConstants;
import io.netty.util.internal.ResourcesUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:26
 * @PackageName:com.pn.controller
 * @ClassName: ProductController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {

//    注入StoreService
    @Autowired
    private StoreService storeService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private TokenUtils tokenUtils;

//  查询所有store仓库的url接口product/store-list
    @GetMapping("/store-list")
    public Result storeList(){
        List<Store> stores = storeService.queryStoreAll();
        return Result.ok(stores);
    }

//  查询所有的brand品牌的url接口brand-list
    @GetMapping("/brand-list")
    public Result brandList(){
        List<Brand> brands = brandService.queryBrandAll();
        return Result.ok(brands);
    }

//  查询所有的supply供应商的url接口product/supply-list
    @GetMapping("/supply-list")
    public Result supplyList(){
        List<Supply> supplies = supplyService.querySupplyAll();
        return Result.ok(supplies);
    }

//  查询所有的的place地址的url接口product/place-list
    @GetMapping("/place-list")
    public Result placeList(){
        List<Place> places = placeService.queryPlaceAll();
        return Result.ok(places);
    }

//  查询所有的unit单位的url接口product/unit-list?
    @GetMapping("/unit-list")
    public Result unitList(){
        List<Unit> units = unitService.queryUnitAll();
        return Result.ok(units);
    }

//  分页查询商品的url接口product/product-page-list
    @GetMapping("/product-page-list")
    public Result productPage(Page page, Product product){
        page = productService.queryProductPage(page, product);
        return Result.ok(page);
    }

//  查询商品类别树的url接口prduct/category-tree
    @GetMapping("/category-tree")
    public Result categoryTree(){
        List<ProductType> productTypes = productTypeService.categoryTree();
        return Result.ok(productTypes);
    }

/*  上传商品图片的url接口product/img-upload
*
*   参数MultipartFile file -- 表示封装了请求参数名叫file的上传的图片
*   file.transferTo(上传的文件保存到磁盘文件的File对象） -- 实现文件的上传
* */
    @Value("${file.upload-path}")//将配置文件中的file.upload-path属性注入到控制器的成员属性upload
    private String uploadPath;

    @CrossOrigin//表示url接口允许跨域请求
    @RequestMapping("/img-upload")
    public Result uploadImge(MultipartFile file){
        try {
            /*
            * 拿到图片上传到目录路径的file对象， -- classpath:static/img/upload
            *
            * 因为图片上传到目录路径是个类路径（resource下路径/classes下的路径，就是带有classpath前缀）
            * 所以不能直接将路径封装到File对象，使用类路径资源工具类ResourceUtils的方法getFile（）来解析类路径
            * 并且拿到封装类路径的File对象
            * */
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
            //拿到图片上传到目录路径的磁盘路径
            String uploadDirPath = uploadDirFile.getAbsolutePath();
            //拿到上传的图片的名称
            String filename = file.getOriginalFilename();
            //拿到上传的图片要保存的磁盘文件的路径
            String uploadFilePath = uploadDirPath+"\\"+filename;
            //上传图片
            file.transferTo(new File(uploadFilePath));
            return Result.ok("图片上传成功");
        } catch (Exception e) {
            throw new BusinessException("上传图片失败！");
        }
    }

    //添加商品的url接口product/product-add
    @PostMapping("/product-add")
    public Result addProduct(@RequestBody Product product,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        product.setCreateBy(tokenUtils.getCurrentUser(token).getUserId());
        return productService.savaProduct(product);
    }

    //商品下架操作的url接口product/state-change
    @PutMapping("/state-change")
    public Result stateChange(@RequestBody Product product){
//        return productService.modifyProductState(product);
        return productService.modifyProduct(product);
    }

    //删除商品的url接口product/product-delete/23
    @DeleteMapping("product-delete/{product_id}")
    public Result deleteProduct(@PathVariable Integer product_id){
        return productService.removeProduct(product_id);
    }

    //批量删除商品的url接口product/product-list-delete
    @DeleteMapping("/product-list-delete")
    public Result deleteProducts(@RequestBody List<Integer> productIds){
        return productService.removeProducts(productIds);
    }

    //修改商品的url接口product/product-update
    @PutMapping("/product-update")
    public Result updateProduct(@RequestBody Product product ,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        product.setUpdateBy(tokenUtils.getCurrentUser(token).getUserId());
        return productService.modifyProduct(product);
    }

    //  导出数据
    @GetMapping("/exportTable")
    public Result exportTable(Page page,Product product){
        page = productService.queryProductPage(page, product);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }







}
