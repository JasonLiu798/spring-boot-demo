package com.jason798.timing.domain.gen;

import java.util.ArrayList;
import java.util.List;

public class GenTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GenTaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTidIsNull() {
            addCriterion("TID is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("TID is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Long value) {
            addCriterion("TID =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Long value) {
            addCriterion("TID <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Long value) {
            addCriterion("TID >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Long value) {
            addCriterion("TID >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Long value) {
            addCriterion("TID <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Long value) {
            addCriterion("TID <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Long> values) {
            addCriterion("TID in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Long> values) {
            addCriterion("TID not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Long value1, Long value2) {
            addCriterion("TID between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Long value1, Long value2) {
            addCriterion("TID not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTkeyIsNull() {
            addCriterion("TKEY is null");
            return (Criteria) this;
        }

        public Criteria andTkeyIsNotNull() {
            addCriterion("TKEY is not null");
            return (Criteria) this;
        }

        public Criteria andTkeyEqualTo(String value) {
            addCriterion("TKEY =", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotEqualTo(String value) {
            addCriterion("TKEY <>", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyGreaterThan(String value) {
            addCriterion("TKEY >", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyGreaterThanOrEqualTo(String value) {
            addCriterion("TKEY >=", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLessThan(String value) {
            addCriterion("TKEY <", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLessThanOrEqualTo(String value) {
            addCriterion("TKEY <=", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyLike(String value) {
            addCriterion("TKEY like", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotLike(String value) {
            addCriterion("TKEY not like", value, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyIn(List<String> values) {
            addCriterion("TKEY in", values, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotIn(List<String> values) {
            addCriterion("TKEY not in", values, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyBetween(String value1, String value2) {
            addCriterion("TKEY between", value1, value2, "tkey");
            return (Criteria) this;
        }

        public Criteria andTkeyNotBetween(String value1, String value2) {
            addCriterion("TKEY not between", value1, value2, "tkey");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNull() {
            addCriterion("SERVICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNotNull() {
            addCriterion("SERVICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andServiceNameEqualTo(String value) {
            addCriterion("SERVICE_NAME =", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotEqualTo(String value) {
            addCriterion("SERVICE_NAME <>", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThan(String value) {
            addCriterion("SERVICE_NAME >", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_NAME >=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThan(String value) {
            addCriterion("SERVICE_NAME <", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_NAME <=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLike(String value) {
            addCriterion("SERVICE_NAME like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotLike(String value) {
            addCriterion("SERVICE_NAME not like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameIn(List<String> values) {
            addCriterion("SERVICE_NAME in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotIn(List<String> values) {
            addCriterion("SERVICE_NAME not in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameBetween(String value1, String value2) {
            addCriterion("SERVICE_NAME between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotBetween(String value1, String value2) {
            addCriterion("SERVICE_NAME not between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNull() {
            addCriterion("CONF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNotNull() {
            addCriterion("CONF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andConfTypeEqualTo(String value) {
            addCriterion("CONF_TYPE =", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotEqualTo(String value) {
            addCriterion("CONF_TYPE <>", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThan(String value) {
            addCriterion("CONF_TYPE >", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_TYPE >=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThan(String value) {
            addCriterion("CONF_TYPE <", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThanOrEqualTo(String value) {
            addCriterion("CONF_TYPE <=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLike(String value) {
            addCriterion("CONF_TYPE like", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotLike(String value) {
            addCriterion("CONF_TYPE not like", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeIn(List<String> values) {
            addCriterion("CONF_TYPE in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotIn(List<String> values) {
            addCriterion("CONF_TYPE not in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeBetween(String value1, String value2) {
            addCriterion("CONF_TYPE between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotBetween(String value1, String value2) {
            addCriterion("CONF_TYPE not between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionIsNull() {
            addCriterion("CONF_CRON_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionIsNotNull() {
            addCriterion("CONF_CRON_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION =", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION <>", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionGreaterThan(String value) {
            addCriterion("CONF_CRON_EXPRESSION >", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION >=", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLessThan(String value) {
            addCriterion("CONF_CRON_EXPRESSION <", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLessThanOrEqualTo(String value) {
            addCriterion("CONF_CRON_EXPRESSION <=", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLike(String value) {
            addCriterion("CONF_CRON_EXPRESSION like", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotLike(String value) {
            addCriterion("CONF_CRON_EXPRESSION not like", value, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionIn(List<String> values) {
            addCriterion("CONF_CRON_EXPRESSION in", values, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotIn(List<String> values) {
            addCriterion("CONF_CRON_EXPRESSION not in", values, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionBetween(String value1, String value2) {
            addCriterion("CONF_CRON_EXPRESSION between", value1, value2, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionNotBetween(String value1, String value2) {
            addCriterion("CONF_CRON_EXPRESSION not between", value1, value2, "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmIsNull() {
            addCriterion("CONF_DELAY_TM is null");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmIsNotNull() {
            addCriterion("CONF_DELAY_TM is not null");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM =", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmNotEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM <>", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmGreaterThan(Long value) {
            addCriterion("CONF_DELAY_TM >", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM >=", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmLessThan(Long value) {
            addCriterion("CONF_DELAY_TM <", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmLessThanOrEqualTo(Long value) {
            addCriterion("CONF_DELAY_TM <=", value, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmIn(List<Long> values) {
            addCriterion("CONF_DELAY_TM in", values, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmNotIn(List<Long> values) {
            addCriterion("CONF_DELAY_TM not in", values, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmBetween(Long value1, Long value2) {
            addCriterion("CONF_DELAY_TM between", value1, value2, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfDelayTmNotBetween(Long value1, Long value2) {
            addCriterion("CONF_DELAY_TM not between", value1, value2, "confDelayTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmIsNull() {
            addCriterion("CONF_INTERVAL_TM is null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmIsNotNull() {
            addCriterion("CONF_INTERVAL_TM is not null");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM =", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmNotEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM <>", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmGreaterThan(Long value) {
            addCriterion("CONF_INTERVAL_TM >", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM >=", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmLessThan(Long value) {
            addCriterion("CONF_INTERVAL_TM <", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmLessThanOrEqualTo(Long value) {
            addCriterion("CONF_INTERVAL_TM <=", value, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmIn(List<Long> values) {
            addCriterion("CONF_INTERVAL_TM in", values, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmNotIn(List<Long> values) {
            addCriterion("CONF_INTERVAL_TM not in", values, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmBetween(Long value1, Long value2) {
            addCriterion("CONF_INTERVAL_TM between", value1, value2, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfIntervalTmNotBetween(Long value1, Long value2) {
            addCriterion("CONF_INTERVAL_TM not between", value1, value2, "confIntervalTm");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIsNull() {
            addCriterion("CONF_EXE_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIsNotNull() {
            addCriterion("CONF_EXE_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES =", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES <>", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesGreaterThan(Long value) {
            addCriterion("CONF_EXE_TIMES >", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesGreaterThanOrEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES >=", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesLessThan(Long value) {
            addCriterion("CONF_EXE_TIMES <", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesLessThanOrEqualTo(Long value) {
            addCriterion("CONF_EXE_TIMES <=", value, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesIn(List<Long> values) {
            addCriterion("CONF_EXE_TIMES in", values, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotIn(List<Long> values) {
            addCriterion("CONF_EXE_TIMES not in", values, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesBetween(Long value1, Long value2) {
            addCriterion("CONF_EXE_TIMES between", value1, value2, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andConfExeTimesNotBetween(Long value1, Long value2) {
            addCriterion("CONF_EXE_TIMES not between", value1, value2, "confExeTimes");
            return (Criteria) this;
        }

        public Criteria andProcessorIsNull() {
            addCriterion("PROCESSOR is null");
            return (Criteria) this;
        }

        public Criteria andProcessorIsNotNull() {
            addCriterion("PROCESSOR is not null");
            return (Criteria) this;
        }

        public Criteria andProcessorEqualTo(String value) {
            addCriterion("PROCESSOR =", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotEqualTo(String value) {
            addCriterion("PROCESSOR <>", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorGreaterThan(String value) {
            addCriterion("PROCESSOR >", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESSOR >=", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLessThan(String value) {
            addCriterion("PROCESSOR <", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLessThanOrEqualTo(String value) {
            addCriterion("PROCESSOR <=", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLike(String value) {
            addCriterion("PROCESSOR like", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotLike(String value) {
            addCriterion("PROCESSOR not like", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorIn(List<String> values) {
            addCriterion("PROCESSOR in", values, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotIn(List<String> values) {
            addCriterion("PROCESSOR not in", values, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorBetween(String value1, String value2) {
            addCriterion("PROCESSOR between", value1, value2, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotBetween(String value1, String value2) {
            addCriterion("PROCESSOR not between", value1, value2, "processor");
            return (Criteria) this;
        }

        public Criteria andStatusEndIsNull() {
            addCriterion("STATUS_END is null");
            return (Criteria) this;
        }

        public Criteria andStatusEndIsNotNull() {
            addCriterion("STATUS_END is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEndEqualTo(String value) {
            addCriterion("STATUS_END =", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndNotEqualTo(String value) {
            addCriterion("STATUS_END <>", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndGreaterThan(String value) {
            addCriterion("STATUS_END >", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_END >=", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndLessThan(String value) {
            addCriterion("STATUS_END <", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndLessThanOrEqualTo(String value) {
            addCriterion("STATUS_END <=", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndLike(String value) {
            addCriterion("STATUS_END like", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndNotLike(String value) {
            addCriterion("STATUS_END not like", value, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndIn(List<String> values) {
            addCriterion("STATUS_END in", values, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndNotIn(List<String> values) {
            addCriterion("STATUS_END not in", values, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndBetween(String value1, String value2) {
            addCriterion("STATUS_END between", value1, value2, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andStatusEndNotBetween(String value1, String value2) {
            addCriterion("STATUS_END not between", value1, value2, "statusEnd");
            return (Criteria) this;
        }

        public Criteria andMutexIsNull() {
            addCriterion("MUTEX is null");
            return (Criteria) this;
        }

        public Criteria andMutexIsNotNull() {
            addCriterion("MUTEX is not null");
            return (Criteria) this;
        }

        public Criteria andMutexEqualTo(String value) {
            addCriterion("MUTEX =", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexNotEqualTo(String value) {
            addCriterion("MUTEX <>", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexGreaterThan(String value) {
            addCriterion("MUTEX >", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexGreaterThanOrEqualTo(String value) {
            addCriterion("MUTEX >=", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexLessThan(String value) {
            addCriterion("MUTEX <", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexLessThanOrEqualTo(String value) {
            addCriterion("MUTEX <=", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexLike(String value) {
            addCriterion("MUTEX like", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexNotLike(String value) {
            addCriterion("MUTEX not like", value, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexIn(List<String> values) {
            addCriterion("MUTEX in", values, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexNotIn(List<String> values) {
            addCriterion("MUTEX not in", values, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexBetween(String value1, String value2) {
            addCriterion("MUTEX between", value1, value2, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexNotBetween(String value1, String value2) {
            addCriterion("MUTEX not between", value1, value2, "mutex");
            return (Criteria) this;
        }

        public Criteria andMutexTmIsNull() {
            addCriterion("MUTEX_TM is null");
            return (Criteria) this;
        }

        public Criteria andMutexTmIsNotNull() {
            addCriterion("MUTEX_TM is not null");
            return (Criteria) this;
        }

        public Criteria andMutexTmEqualTo(Long value) {
            addCriterion("MUTEX_TM =", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmNotEqualTo(Long value) {
            addCriterion("MUTEX_TM <>", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmGreaterThan(Long value) {
            addCriterion("MUTEX_TM >", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmGreaterThanOrEqualTo(Long value) {
            addCriterion("MUTEX_TM >=", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmLessThan(Long value) {
            addCriterion("MUTEX_TM <", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmLessThanOrEqualTo(Long value) {
            addCriterion("MUTEX_TM <=", value, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmIn(List<Long> values) {
            addCriterion("MUTEX_TM in", values, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmNotIn(List<Long> values) {
            addCriterion("MUTEX_TM not in", values, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmBetween(Long value1, Long value2) {
            addCriterion("MUTEX_TM between", value1, value2, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andMutexTmNotBetween(Long value1, Long value2) {
            addCriterion("MUTEX_TM not between", value1, value2, "mutexTm");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAliveTmIsNull() {
            addCriterion("ALIVE_TM is null");
            return (Criteria) this;
        }

        public Criteria andAliveTmIsNotNull() {
            addCriterion("ALIVE_TM is not null");
            return (Criteria) this;
        }

        public Criteria andAliveTmEqualTo(Long value) {
            addCriterion("ALIVE_TM =", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmNotEqualTo(Long value) {
            addCriterion("ALIVE_TM <>", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmGreaterThan(Long value) {
            addCriterion("ALIVE_TM >", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmGreaterThanOrEqualTo(Long value) {
            addCriterion("ALIVE_TM >=", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmLessThan(Long value) {
            addCriterion("ALIVE_TM <", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmLessThanOrEqualTo(Long value) {
            addCriterion("ALIVE_TM <=", value, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmIn(List<Long> values) {
            addCriterion("ALIVE_TM in", values, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmNotIn(List<Long> values) {
            addCriterion("ALIVE_TM not in", values, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmBetween(Long value1, Long value2) {
            addCriterion("ALIVE_TM between", value1, value2, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andAliveTmNotBetween(Long value1, Long value2) {
            addCriterion("ALIVE_TM not between", value1, value2, "aliveTm");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("VALID is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("VALID is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(String value) {
            addCriterion("VALID =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(String value) {
            addCriterion("VALID <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(String value) {
            addCriterion("VALID >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(String value) {
            addCriterion("VALID >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(String value) {
            addCriterion("VALID <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(String value) {
            addCriterion("VALID <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLike(String value) {
            addCriterion("VALID like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotLike(String value) {
            addCriterion("VALID not like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<String> values) {
            addCriterion("VALID in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<String> values) {
            addCriterion("VALID not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(String value1, String value2) {
            addCriterion("VALID between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(String value1, String value2) {
            addCriterion("VALID not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andTkeyLikeInsensitive(String value) {
            addCriterion("upper(TKEY) like", value.toUpperCase(), "tkey");
            return (Criteria) this;
        }

        public Criteria andServiceNameLikeInsensitive(String value) {
            addCriterion("upper(SERVICE_NAME) like", value.toUpperCase(), "serviceName");
            return (Criteria) this;
        }

        public Criteria andConfTypeLikeInsensitive(String value) {
            addCriterion("upper(CONF_TYPE) like", value.toUpperCase(), "confType");
            return (Criteria) this;
        }

        public Criteria andConfCronExpressionLikeInsensitive(String value) {
            addCriterion("upper(CONF_CRON_EXPRESSION) like", value.toUpperCase(), "confCronExpression");
            return (Criteria) this;
        }

        public Criteria andProcessorLikeInsensitive(String value) {
            addCriterion("upper(PROCESSOR) like", value.toUpperCase(), "processor");
            return (Criteria) this;
        }

        public Criteria andStatusEndLikeInsensitive(String value) {
            addCriterion("upper(STATUS_END) like", value.toUpperCase(), "statusEnd");
            return (Criteria) this;
        }

        public Criteria andMutexLikeInsensitive(String value) {
            addCriterion("upper(MUTEX) like", value.toUpperCase(), "mutex");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andValidLikeInsensitive(String value) {
            addCriterion("upper(VALID) like", value.toUpperCase(), "valid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}