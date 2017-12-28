package cn.et.test.dao;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

import cn.et.test.entity.cook.Cook;
import cn.et.test.entity.cook.CookExample;

public interface CookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @SelectProvider(type=CookSqlProvider.class, method="countByExample")
    long countByExample(CookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @DeleteProvider(type=CookSqlProvider.class, method="deleteByExample")
    int deleteByExample(CookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @Delete({
        "delete from cook",
        "where fid = #{fid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer fid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @Insert({
        "insert into cook (fid, fname, ",
        "cid)",
        "values (#{fid,jdbcType=INTEGER}, #{fname,jdbcType=VARCHAR}, ",
        "#{cid,jdbcType=INTEGER})"
    })
    int insert(Cook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @InsertProvider(type=CookSqlProvider.class, method="insertSelective")
    int insertSelective(Cook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @SelectProvider(type=CookSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="fid", property="fid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fname", property="fname", jdbcType=JdbcType.VARCHAR),
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER)
    })
    List<Cook> selectByExampleWithRowbounds(CookExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @SelectProvider(type=CookSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="fid", property="fid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fname", property="fname", jdbcType=JdbcType.VARCHAR),
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER)
    })
    List<Cook> selectByExample(CookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @Select({
        "select",
        "fid, fname, cid",
        "from cook",
        "where fid = #{fid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="fid", property="fid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fname", property="fname", jdbcType=JdbcType.VARCHAR),
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER)
    })
    Cook selectByPrimaryKey(Integer fid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @UpdateProvider(type=CookSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Cook record, @Param("example") CookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @UpdateProvider(type=CookSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Cook record, @Param("example") CookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @UpdateProvider(type=CookSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Cook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cook
     *
     * @mbg.generated Wed Dec 13 16:36:58 CST 2017
     */
    @Update({
        "update cook",
        "set fname = #{fname,jdbcType=VARCHAR},",
          "cid = #{cid,jdbcType=INTEGER}",
        "where fid = #{fid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Cook record);
}